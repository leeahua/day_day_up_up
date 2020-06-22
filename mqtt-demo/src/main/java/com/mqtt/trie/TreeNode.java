package com.mqtt.trie;

import java.util.concurrent.atomic.AtomicReference;

/***
* 节点信息
 *
* @author liyaohua
* @date 15:55 2020/5/22
* **/
public class TreeNode {

    private String content;

    private  AtomicReference<TreeNode> mainNode = new AtomicReference<>();

}
