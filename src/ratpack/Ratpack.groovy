import com.iai.SpringConfig
import com.iai.UserRepository
import ratpack.groovy.template.MarkupTemplateModule
import ratpack.spring.Spring

import static ratpack.groovy.Groovy.groovyMarkupTemplate
import static ratpack.groovy.Groovy.ratpack

ratpack {
    bindings {
        module MarkupTemplateModule
    }

    handlers {
        register Spring.spring(SpringConfig)

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
            get(':username') { UserRepository userRepository -> // Ratpack will lookup this object from the registry and provide to the handler
                println userRepository.findUserByUsername(context.pathTokens["username"])

                // context is implicit so we can also do
                println userRepository.findUserByUsername(pathTokens["username"])

                // you can also manually lookup object from the registry if you want to
                println context.get(UserRepository).findUserByUsername(pathTokens["username"])
                println get(UserRepository).findUserByUsername(pathTokens["username"])

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
