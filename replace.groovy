#!/usr/bin/env groovy

//example usage:
//cat foo.gsp| replace.groovy "/foo/(\w+.css)" "\\\${createLinkTo(dir:'/theme/zen/css/',file:'\$1')}"

def replace(text, regex, replacementPattern) {
    def matcher = text =~ regex
    matcher.replaceAll(replacementPattern)
}

//read text from stdin
def text = new StringBuffer()
System.in.eachLine{line-> 
   text<<line<<"\n"
}

println replace(text, args[0], args[1])
