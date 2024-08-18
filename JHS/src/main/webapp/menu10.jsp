<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>포트폴리오</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
    
   		body 
   		{
            background-color: #d3d3d3; /* Light gray background */
        }
        .portfolio-container {
            max-width: 800px;
            margin: auto;
            padding: 20px;
        }
        .profile-photo {
            max-width: 150px;
            border-radius: 50%;
            margin-bottom: 20px;
        }
        .wrap {
            background-color: #f8f9fa;
            padding: 20px;
            border-radius: 10px;
        }
        .common_box {
            padding: 20px;
            background: white;
            border: 1px solid #e3e3e3;
            border-radius: 10px;
        }
        .card_box {
            display: flex;
            align-items: center;
        }
        .card_left {
            flex: 1;
        }
        .card_job {
            margin-bottom: 15px;
        }
        .name_ko {
            display: block;
            font-size: 1.5rem;
            font-weight: bold;
        }
        .name_eng {
            display: block;
            font-size: 1.25rem;
            color: #555;
        }
        .job {
            display: block;
            font-size: 1rem;
            color: #777;
        }
        .item_list {
            margin-top: 15px;
        }
        .item_type span {
            display: block;
            font-weight: bold;
        }
        .item_list div span {
            display: block;
            margin-bottom: 5px;
        }
        .item_list a {
            color: #007bff;
            text-decoration: none;
        }
        .item_list a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<%@ include file="menu.jsp" %>
<br>


    <div class="portfolio-container">
        <div class="wrap">
            <div class="common_box card_box">
                <div class="card_left">
                    <img src="./images/jhs.jpg" alt="정 회 석" class="profile-photo">
                    <div class="card_job">
                        <span class="name_ko">정 회 석</span>
                        <span class="name_eng">Jeong Hoe Soek</span>
                        <span class="job">웹 개발자</span>
                    </div>
                    <div class="item_list">
                        <span><strong>Mail:</strong> whrudtnr5@naver.com</span>
                        <span><strong>Site:</strong> <a href="http://JeongHoeSoek.github.io">JeongHoeSoek.github.io</a></span>
                    </div>
                </div>
            </div>
            <br>
            <div class="common_box">
                <h5>대학교</h5>
                <p>조선대학교 컴퓨터공학과 4학년</p>
            </div>
            <br>
            <div class="common_box">
                <h5>직무교육</h5>
                <p>패스트캠퍼스 React & Redux 로 시작하는 웹 프로그래밍</p>
                <p>패스트캠퍼스 나도 할 수 있는 Java&Spring 웹 개발 종합반</p>
            </div>
            <br>
            <div class="common_box">
                <h5>프로젝트</h5>
                <p>캡스톤 디자인 4인 프로젝트, 낙상감지 앱 프론트엔드 파트</p>
                <p>클라우드 시스템, 포트폴리오 반응형 웹 개발</p>
                <p>앱 프로그래밍, 안드로이드 기반 포트폴리오 메이킹 앱 개발</p>
                <p>오픈소스 HW, 슈 케어 시스템 2인 개발</p>
                <p>쇼크 드라이버, 2D 플랫폼 게임 개발</p>
            </div>
        </div>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>