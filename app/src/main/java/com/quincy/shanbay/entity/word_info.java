package com.quincy.shanbay.entity;

/**
 * Created by quincy on 17/2/3.
 */

public class word_info {

    private String word;            //单词
    private String translation;     //翻译
    private String phonetic_symbol; //音标
    private String sentence;        //例句
    private int id;
    public String toString(){
        return word + "/n" + translation + "n/" + phonetic_symbol + "n/"+sentence;
    }
}
