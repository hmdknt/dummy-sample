package com.kento.dummy.domain.model;

import lombok.Data;

import java.util.List;

@Data
public class GoogleTask {

    // リソースタイプ（tasks#taskLists）
    private String kind;

    // タスクリストの識別子
    private String id;

    // リソースのetag
    private String etag;

    // タスクリストのタイトル
    private String title;

    //
    private String selfLink;

    //
    // private Datetime updated;

    // 次のページで利用する
    private String nextPageToken;

    // タスクリストのコレクション
    private List<String> items;

}
