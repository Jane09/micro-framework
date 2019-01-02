package com.micro.framework.api.vo.search;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author tb
 * @date 2019/1/2 11:22
 */
@Getter
@Setter
public class IndexVo implements Comparable<IndexVo>, Serializable {

    private Long id;
    private String title;
    private String keywords;
    private String descripton;
    private String postDate;
    private String url;
    /*相似度*/
    private float score;

    public IndexVo() {
        super();
    }

    public IndexVo(Long id, String keywords, String descripton, String postdate, float score) {
        super();
        this.id = id;
        this.keywords = keywords;
        this.score = score;
        this.descripton=descripton;
        this.postDate=postdate;
    }

    @Override
    public int compareTo(IndexVo o) {
        if(this.score < o.getScore()){
            return 1;
        }else if(this.score > o.getScore()){
            return -1;
        }
        return 0;
    }
}
