package com.zp.plugin.transform

import org.gradle.api.Plugin
import org.gradle.api.Project


class MyTransform implements Plugin<Project> {

    @Override
    void apply(Project project) {
        System.out.println("zp::: MyTransform....")
    }
}