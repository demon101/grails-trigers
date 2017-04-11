package grails.trigers

import grails.transaction.Transactional

class TestController {

    @Transactional
    def index() {

        def d = SomeDomain.findByEngName("name2")

        d.engName = "name1"
        d.save()
        render "ok"
    }

    def tags(){

    }
}
