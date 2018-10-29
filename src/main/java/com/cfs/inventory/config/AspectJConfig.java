package com.cfs.inventory.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@ComponentScan("com.cfs.inventory.monitor")
@EnableAspectJAutoProxy
public class AspectJConfig {

}
