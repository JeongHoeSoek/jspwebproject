$(function(){
    getHotJson();
});

function getHotJson(){
    $.getJSON("json/hot.json", function(data){
        // 테이블 생성
        $("table").attr("border", "1");
        
        // 테이블 헤더 생성
        $("thead").append(
            "<tr>"+
            "<th>일시</th>"+
            "<th>지점</th>"+
            "<th>폭염여부</th>"+
            "<th>최고체감온도(°C)</th>"+
            "<th>최고기온(°C)</th>"+
            "<th>평균기온(°C)</th>"+
            "<th>최저기온(°C)</th>"+
            "<th>평균상대습도(%)</th>"+
            "<th>폭염특보</th>"+
            "</tr>"
        );

        // 데이터 출력 (처음 10개 항목만)
        for (var i = 0; i < Math.min(10, data.length); i++) {
            var item = data[i];
            var row = "<tr>" +
                "<td>" + item.일시 + "</td>" +
                "<td>" + item.지점 + "</td>" +
                "<td>" + item["폭염여부(O/X)"] + "</td>" +
                "<td>" + item["최고체감온도(°C)"] + "</td>" +
                "<td>" + item["최고기온(°C)"] + "</td>" +
                "<td>" + item["평균기온(°C)"] + "</td>" +
                "<td>" + item["최저기온(°C)"] + "</td>" +
                "<td>" + item["평균상대습도(%)"] + "</td>" +
                "<td>" + item["폭염특보(O/X)"] + "</td>" +
                "</tr>";
            $("tbody").append(row);
        }
    });
}