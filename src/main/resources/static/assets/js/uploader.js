async function uploadFile() {
    let file = document.getElementById("upload-file").files[0];
    let formData = new FormData();

    formData.append("file", file);
    await fetch('http://localhost:8080/upload', {
        method: "POST",
        body: formData
    });
}