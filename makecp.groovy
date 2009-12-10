#!/usr/bin/env groovy

cp ="export CLASSPATH="

def iterate(File root){
    def dir = root.listFiles()

    dir.each{File file->
        if(file.name.indexOf(".jar")>-1){
            cp=cp+":${file.canonicalPath}"
        }else if(file.isDirectory()){
            if(file.name!=".svn"){
                iterate(file)
            }
        }
    }
}


root = new File(args[0])
iterate(root)
println cp
