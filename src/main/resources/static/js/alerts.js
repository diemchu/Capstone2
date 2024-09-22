// src/main/resources/static/js/script.js
function showAlert(message) {
    Swal.fire({
        title: '알림',
        text: message,
        icon: 'warning',
        confirmButtonText: '확인',
        confirmButtonColor: '#007BFF'
    });
}

document.addEventListener('DOMContentLoaded', function() {
    var message = document.getElementById('alertMessage').value; // Lấy giá trị từ một input ẩn
    if (message && message !== '') {
        showAlert(message);
    }
});



