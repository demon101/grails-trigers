package grails.trigers

class SomeDomain {

    String engName
    String linkName = ""

    static constraints = {
        engName nullable: false, blank:false
        linkName unique: true, nullable: false, blank:false
    }

    static mapping = {
        cache 'nonstrict-read-write'
    }

    def beforeValidate(){
        log.error "beforeValidate(id=${id})"

        if (!id || isDirty('engName')){
            def oldLinkName = linkName
            linkName = engName.toLowerCase() // some dummy transformation
            if (SomeDomain.countByLinkNameAndIdNotEqual(linkName, id?:0L)){ //causes beforeUpdate !!!
                linkName = "$engName ${1}".toLowerCase()
                log.error("linkName '${oldLinkName}' already exist, new link name is ${linkName}")
            }
        }

        log.error "beforeValidate end(id=${id})"
    }

    def beforeInsert () {
        OldLink.findByOldAndTypeId(linkName, null)?.delete()
        return true
    }

    def beforeUpdate () {
        log.error "beforeUpdate(id=${id})"

        if (isDirty('linkName')){
            def oldLink = getPersistentValue('linkName')
            if (oldLink != linkName){
                log.error "Create OldLink $oldLink -> $linkName"
                def l = new OldLink(old: oldLink, current: linkName)
                if (!l.save()) log.error l.errors
            }
        }

        log.error "beforeUpdate end(id=${id})"
        return true
    }

}
