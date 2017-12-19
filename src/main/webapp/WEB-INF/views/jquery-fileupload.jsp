<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>jQuery File Upload Example</title>
</head>
<body>
<input id="fileupload" type="file" name="files[]" data-url="server/php/" multiple>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/statics/jquery/fileupload/jquery.ui.widget.js"></script>
<script src="${pageContext.request.contextPath}/statics/jquery/fileupload/jquery.iframe-transport.js"></script>
<script src="${pageContext.request.contextPath}/statics/jquery/fileupload/jquery.fileupload.js"></script>
<script>
    $(function () {
        $('#fileupload').fileupload({
            dataType: 'json',
            done: function (e, data) {
                $.each(data.result.files, function (index, file) {
                    $('<p/>').text(file.name).appendTo(document.body);
                });
            }
        });
    });
</script>
</body>
</html>