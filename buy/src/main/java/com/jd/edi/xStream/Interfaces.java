package com.jd.edi.xStream;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.ToString;

@XStreamAlias("interface")
@AllArgsConstructor
@ToString
public class Interfaces {

    @XStreamAlias("name")
    private String name;

    @XStreamAlias("alias")
    private String alias;


}
