class Hybel {
    constructor() {
        this.hybel = document.querySelector("#hybel");
        
        this.load();
        
        
    }
    
    load(){
        let user = document.getElementById('users').value;
        fetch('api/' +user  )
             .then(response =>{
                if(response.ok){
                    return response.json();
                }
                
                throw new Error("Failed to load list of conversations");
        })
                .then(json=> this.loadHybel(json))
                .catch(e => console.log("error: " + e.message));
    }
    
        loadHybel(json) {
        this.hybel.innerHTML = '';
        let ul = document.createElement('ul');
        for(let i = 0; i < json.length; i++) {
            
            let li = document.createElement('li');
            let a = document.createElement('a');
            a.innerHTML = json[i].id;
            a.href = "user.html" ;
            li.appendChild(a);
            ul.appendChild(li);

            this.hybel.appendChild(ul);
        }
        
    }
}

let h = new Hybel();

