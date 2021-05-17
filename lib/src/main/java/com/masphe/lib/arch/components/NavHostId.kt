package com.masphe.lib.arch.components

import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(RetentionPolicy.RUNTIME)
annotation class NavHostId(val value: Int)