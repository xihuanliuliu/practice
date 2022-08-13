package com.jd.edi.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamMap {
    /**
     * map：接收 Lambda ，将元素转换为其他形式或提取信息；接受一个函数作为参数，
     *    该函数会被应用到每个元素上，并将其映射成一个新的元素
     *
     *
     * flatMap：接收一个函数作为参数，将流中每一个值都换成另一个流，然后把所有流重新连接成一个流
     */
    public static void map() {
        List<String> list = Arrays.asList("a", "b", "c");
        list.stream()
                .map((str) -> str.toUpperCase())
                .forEach(System.out::println);
    }

    public static Stream<Character> filterCharacter(String str){
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }

        return list.stream();
    }


    public static void flatMap(){
        List<String> list = Arrays.asList("ab", "b", "c");
        list.stream()
                .flatMap(StreamMap::filterCharacter)
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        map();
        System.out.println("--------------");
        flatMap();
    }
}
