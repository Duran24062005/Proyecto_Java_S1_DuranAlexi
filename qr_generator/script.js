let qr;

function generarQR() {
    const texto = document.getElementById("qrText").value.trim();
    const qrCode = document.getElementById("qrCode");

    qrCode.innerHTML = "";

    if (!texto) {
        alert("Ingresa un texto o URL");
        return;
    }

    qr = new QRCode(qrCode, {
        text: texto,
        width: 200,
        height: 200,
        colorDark: "#000000",
        colorLight: "#ffffff",
        correctLevel: QRCode.CorrectLevel.H
    });
}

function descargarQR() {
    const wrapper = document.getElementById("qrWrapper");
    const canvas = wrapper.querySelector("canvas");

    if (!canvas) {
        alert("Primero genera un QR");
        return;
    }

    const enlace = document.createElement("a");
    enlace.href = canvas.toDataURL("image/png");
    enlace.download = "codigo-qr.png";
    enlace.click();
}