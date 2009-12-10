#!/usr/bin/env groovy

import java.util.jar.*

def findPattern(String pattern, File jarFile){
    def jf = new JarFile(jarFile)
    def found = jf.entries().findAll{JarEntry e-> e.name.indexOf(pattern)>-1}
    found.each{entry-> println "jarFile: $jarFile class: $entry" }
}

def recursiveFind(String pattern, String dirName){
    File dir = new File(dirName)

    File[] dirEntries = dir.listFiles()
    dirEntries.each{File entry->
        if(entry.isFile()){
            if(entry.name.indexOf(".jar")>-1 || entry.name.indexOf(".zip")>-1){
                findPattern(pattern, entry)
            }
        }else{
            recursiveFind(pattern, entry.name)
        }
    }
}
String pattern = args[0]
recursiveFind(pattern, ".")
