package com.jd.edi.xStream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@XStreamAlias("partner")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Partner {

    @XStreamAlias("name")
    @XStreamAsAttribute
    private String name;

    @XStreamAlias("path")
    @XStreamAsAttribute
    private String filePath;

    @XStreamAlias("alias")
    private String alias;

    @XStreamAlias("interfaces")
    // @XStreamImplicit 不要<interfaces>这个标签
    private List<Interfaces> interfacesList;


}
