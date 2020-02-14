package com.cmz.configuration;

import com.cmz.binding.Constants;
import com.cmz.binding.MapperMethod;
import com.cmz.binding.MapperRegister;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 读取xml配置 加载到内存
 * @Author: chenmingzhe
 * @Date: 2020/2/13 22:18
 */
@Getter
@Setter
@Slf4j
public class Configuration {

    private InputStream inputStream;
    MapperRegister mapperRegister = new MapperRegister();

    public void loadConfigurations(){
        try {
            Document document = new SAXReader().read(inputStream);
            Element root = document.getRootElement();
            List<Element> mappers = root.element(Constants.ELEMENT_MAPPERS)
                    .elements(Constants.ELEMENT_MAPPER);
            for (Element mapper : mappers) {
                if (mapper.attribute(Constants.ELEMENT_RESOURCE)!=null) {
                    try {
                        mapperRegister.setKnownMappers(
                                loadMapperXmlConfig(mapper.attribute(Constants.ELEMENT_RESOURCE).getText()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析**mapper.xml
     * @param resource
     * @return
     * @throws DocumentException
     * @throws IOException
     */
    private Map<String, MapperMethod> loadMapperXmlConfig(String resource) throws DocumentException, IOException {
            Map<String,MapperMethod> map = new HashMap<String, MapperMethod>(2);
            InputStream inputStream = null;
            try {
                inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
                Document document = new SAXReader().read(inputStream);
                Element root = document.getRootElement();
                if (Constants.ELEMENT_MAPPER.equalsIgnoreCase(root.getName())) {
                    String namespace = root.attribute(Constants.ELEMENT_NAMESPACE).getText();
                    for (Element select : (List<Element>) root.elements(Constants.ELEMENT_SELECT)) {
                        MapperMethod mapperMethod = new MapperMethod();
                        mapperMethod.setSql(select.getText().trim());
                        mapperMethod.setType(Class.forName(
                                select.attribute(Constants.ELEMENT_RESULTTYPE).getText()));
                        map.put(namespace + "." + select.attribute(Constants.ELEMENT_ID).getText(), mapperMethod);
                    }
                }
            }catch (ClassNotFoundException e){
                e.printStackTrace();
            } finally {
                if (inputStream != null){
                     inputStream.close();
                }
            }
            return map;
    }

}
