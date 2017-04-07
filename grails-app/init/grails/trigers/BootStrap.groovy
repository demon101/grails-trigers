package grails.trigers

class BootStrap {

    def init = { servletContext ->

        new SomeDomain(engName: "name1").save(failOnError:true)
        new SomeDomain(engName: "name2").save(failOnError:true)

    }
    def destroy = {
    }
}
