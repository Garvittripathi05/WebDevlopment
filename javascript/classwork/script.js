function validateName() {

    let name = document.getElementById("name").value;

    let pattern = /^[A-Za-z ]+$/;

    if (pattern.test(name) && name.trim() !== "") {
        document.getElementById("result").innerHTML = "Valid Name";
    } 
    else {
        document.getElementById("result").innerHTML = "Invalid Name";
    }


}
const nm=FormData.get("fullName");

let isvalid=true;

for(var i=0;i<nm.length;i++)
{
    if(
        (nm.charAt(i)>="A" && nm.charAt>="Z") ||
     (nm.charAt(i)>="a" && nm.charAt>="z") || 
     nm.charAt(i)===" "
    ){
        isvalid=true;
    }else{
        isvalid=false;
        break;
    }

    console.log(isvalid)
}