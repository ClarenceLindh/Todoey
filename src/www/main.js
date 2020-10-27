var myNodelist = document.getElementsByTagName("LI");
var i;
for (i = 0; i < myNodelist.length; i++){
    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    myNodelist[i].appendChild(span);
}

var close = document.getElementsByClassName("close");
var i;
for (i=0; i < close.length; i++){
    close[i].onclick = function(){
        var div = this.parentElement
        div.style.display = "none";
    }
}

var list = document.querySelector('ul');
list.addEventListener('click', function(ev){
    if (ev.target.tagName === 'LI'){
        ev.target.classList.toggle('checked');
    }
}, false);

function newElement(){
    var li = document.createElement("li");
    var inputValue = document.getElementById("myInput").value;
    var t = document.createTextNode(inputValue);
    li.appendChild(t);
    if (inputValue === ''){
        alert ("You must write something ffs!");
    } else {
        document.getElementById("myUL").appendChild(li);
    }
    document.getElementById("myInput").value = "";

    var span = document.createElement("SPAN");
    var txt = document.createTextNode("\u00D7");
    span.className = "close";
    span.appendChild(txt);
    li.appendChild(span);

    for (i=0; i < close.length; i++){
        close[i].onclick = function(){
            var div = this.parentElement;
            div.style.display = "none";
        }
    }
}

let itemz = [];

async function getItems(){
    let result = await fetch('/rest/items');
     itemz = await result.json();

    console.log(itemz);
    renderItems();
}

function renderItems(){
    let itemList = document.querySelector("#item-list");
    itemList.innerHTML = "";

    for(let items of itemz){
        let itemLi = `
            <li>
                ${items.title}
            </li>
        `;
        itemList.innerHTML += itemLi;
    }
}

async function createItem(){
    let item = {
        title: "Test4"
    }

    let result = await fetch ("/rest/items",{
        method: "POST",
        body: JSON.stringify(item)
    });
    console.log(await result.text());
    getItems();
}