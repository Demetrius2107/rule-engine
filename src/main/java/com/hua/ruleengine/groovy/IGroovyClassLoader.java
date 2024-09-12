package com.hua.ruleengine.groovy;

import com.hua.ruleengine.tree.Node;

import java.util.List;

public interface IGroovyClassLoader {

    Class gengerateClass(List<Node> nodes);
}
