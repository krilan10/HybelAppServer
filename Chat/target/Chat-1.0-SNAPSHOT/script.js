class Convers {
    constructor() {
        this.conv = document.querySelector("#conv");
            
        this.load();
    }
    
    load(){
        fetch('api/messages/conversations')
             .then(response =>{
                if(response.ok){
                    return response.json();
                }
                
                throw new Error("Failed to load list of conversations");
        })
                .then(json=> this.addConversations(json))
                .catch(e => console.log("error: " + e.message));
    }
    

    
    addConversations(json) {
        this.conv.innerHTML = '';
        let ul = document.createElement('ul');
        for(let i = 0; i < json.length; i++) {
            
            let li = document.createElement('li');
            let a = document.createElement('a');
            a.innerHTML = json[i].id;
            a.href = "chat.html?name=" + json[i].id ;
            li.appendChild(a);
            ul.appendChild(li);

            this.conv.appendChild(ul);
        }
        
    }
   
}
let c = new Convers();

function  usrname(){
    document.cookie = "username=;path=/";
    let user = document.forms['userForm']['usr'].value;
    document.cookie = "username="+user+";path=/";
   
    
}



function newConversation(){
    let x = document.forms['form']['conversation'].value;

    fetch('api/messages/new?name=' + x,{method: 'POST'});
    
    }