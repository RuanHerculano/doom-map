function deleteReport(id) {
    $.ajax({
        type: 'POST',
        url: '/report/delete/' + id,
        contentType: 'application/json; charset=utf-8',
        success: function () {
            location.href = '/report';
        }
    });
}
