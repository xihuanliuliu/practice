package com.jd.edi.xStream;

/*



<student name="张三">
    <phone>
        <brand>小米</brand>
        <description>小米手机的描述</description>
    </phone>
    <phone>
        <brand>苹果</brand>
        <description>苹果手机的描述</description>
    </phone>
</student>



@AllArgsConstructor
@ToString
//别名注解
@XStreamAlias("student")
class Student {
    @XStreamAlias("name")
    //把字段节点设置成属性
    @XStreamAsAttribute
    private String studentName;
    //省略集合根节点
    @XStreamImplicit
    private List<Phone> phones;
    //隐藏字段
    @XStreamOmitField
    private int age;
    //设置转换器
    @XStreamConverter(value = BooleanConverter.class, booleans = {false}, strings = {"男", "女"})
    private boolean sex;
}

@AllArgsConstructor
@ToString
@XStreamAlias("phone")
class Phone {
    private String brand;
    private String description;
}


 */