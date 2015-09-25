import com.iai.SpringConfig
import ratpack.exec.Promise
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.registry.Registry
import ratpack.spring.Spring

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

def myRegistry = Registry.builder().add(Spring.spring(SpringConfig)).build()

ratpack {
    bindings {
        module MarkupTemplateModule
    }


    handlers {

        all {
            println "foobar"
            next()
        }
        get {
            render groovyMarkupTemplate("index.gtpl", title: "My Ratpack App")
        }
        get('someUrl') {
            render "a string"
        }
        prefix('api') {
            get(':username') {
                render "hiya"
            }
            post {
                def body = it.request.body
                body.then({
                    println it.text; render it.text
                })
            }
        }

        files { dir "public" }
    }
}
