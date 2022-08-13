package com.jd.edi.utils;

import com.jd.edi.xStream.Interfaces;
import com.jd.edi.xStream.Partner;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XStreamUtil {


    /**
     * 将xml转换为bean
     * @param <T> 泛型
     * @param xml 要转换为bean的xml
     * @return xml转换为bean
     */
    public static <T> T xmlConvertBean(String xml, Class<T> clazz) {
        XStream xStream = new XStream(new DomDriver());
        // 注解
        xStream.processAnnotations(clazz);
        return (T) xStream.fromXML(xml);
    }

    /**
     * obejct转xml文件
     * @param object
     * @return
     */
    public static String beanConvertXml(Object object) {
        XStream xStream = new XStream(new DomDriver());
        xStream.autodetectAnnotations(true);
        String xml = xStream.toXML(object);
        return xml;
    }


    public static void main(String[] args) {
        Partner partner = new Partner();
        String name = "adi";
        partner.setName(name);
        partner.setFilePath("E:\\Java\\xml");
        List<Interfaces> interfaces = new ArrayList<>();
        Interfaces interfaces1 = new Interfaces("query", "query");
        Interfaces interfaces2 = new Interfaces("add", "add");
        Interfaces interfaces3 = new Interfaces("delete", "delete");
        Interfaces interfaces4 = new Interfaces("update", "update");
        interfaces.add(interfaces1);interfaces.add(interfaces2);interfaces.add(interfaces3);interfaces.add(interfaces4);
        partner.setInterfacesList(interfaces);
        String s = XStreamUtil.beanConvertXml(partner);
        System.out.println(s);

        String filePath = "E:\\Java\\xml\\" + name + "\\" + name + ".xml";
        File file = new File(filePath);
        FileUtil.write(file, s);



    }

}
