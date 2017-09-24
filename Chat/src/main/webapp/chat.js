
class PhotoForum {
   constructor() {
       this.photo = document.querySelector("#photo");
       this.forum = document.querySelector("#forum");
       this.message = document.querySelector("#message");
       
       let username = "Anonymous";
       this.name = new URL(document.URL).searchParams.get("name");
       
        if(getUsername()){
           username = getUsername();
       }

       
       this.message.onchange = event => {
          fetch('api/messages/add?name=' + this.name,
            {
             method: 'POST', 
             body : JSON.stringify(new Message(username,event.target.value)),
             headers: {'Content-Type' : 'application/json; charset=UTF-8'}
            })
           .then(response => {
               if(response.ok) {
                    return response.json();
                }
                
                throw new Error("Failed to send message " + event.target.value);
            })
           .then(message => {
               this.message.value = "";
            })
            .catch(exception => console.log("Error: " + exception));
       };
       
       this.worker = new Worker("worker.js");
       this.worker.postMessage({"name" : this.name});
       
       this.worker.onmessage = event => {
           this.forum.innerHTML = '';
           let ul = document.createElement('ul');
           event.data.map(message => {
              let li = document.createElement('li');
              li.innerHTML = `${message.user} - ${message.text}`;
              ul.appendChild(li);
           });
           this.forum.appendChild(ul);
           this.forum.scrollTop = this.forum.scrollHeight;
       };       
   } 
   

   
}

class Message {
    constructor(user, text) {
        this.user = user;
        this.text = text;
        this.version = new Date();
    }
}

let forum = new PhotoForum();


function getUsername(){
    let decodedCookie = decodeURIComponent(document.cookie);
    let ca = decodedCookie.split('=');
    
    return ca[1];
    
}