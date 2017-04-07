package grails.trigers

class OldLink {
    String old
    String current
    Long typeId

    Date dateCreated

    static constraints = {
        old blank:false, maxSize: 500
        typeId nullable: true
        current blank:false, maxSize: 500
    }
}
