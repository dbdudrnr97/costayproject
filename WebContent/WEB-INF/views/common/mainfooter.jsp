<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
		</section>
		<footer>
		<hr />
			<table class="table table-borderless">
				<thead>
					<tr>
						<th id="about" data-lang="about">[about]</th>
						<th id="community" data-lang="community">[community]</th>
						<th id="support" data-lang="support">[support]</th>
					</tr>
				</thead>
				<tbody>
					<tr>
						<td id="about_costay"><strong><a href="<%= request.getContextPath()%>/corpInfo"  data-lang="about_costay">[about_costay]</a></strong></td>
						<td id="review" ><strong><a href="<%= request.getContextPath()%>/board/review/boardList" data-lang="review">[review]</a></strong></td>
						<td id="notice" ><strong><a href="<%= request.getContextPath()%>/board/notice/boardList" data-lang="notice">[notice]</a></strong></td>
					</tr>
					<tr>
						<td id="right_people" ><strong><a href="<%= request.getContextPath()%>/rightPeople" data-lang="right_people">[right_people]</a></strong></td>
						<td id="new_host" ><strong><a href="<%= request.getContextPath()%>/board/newHost/boardList" data-lang="new_host">[new_host]</a></strong></td>
						<td id="faq" ><strong><a href="<%= request.getContextPath()%>/board/faq/boardList" data-lang="faq">[faq]</a></strong></td>
					</tr>
					<tr>
						<td></td>
						<td><strong><a href="./chat.html">고객상담</a></strong></td>
						<td id="terms"><strong><a href="<%= request.getContextPath()%>/terms"  data-lang="terms">[terms]</a></strong></td>
					</tr>
				</tbody>
			</table>
			
			<p>
			<span id="info1" data-lang="info1">[info1]</span>&nbsp&nbsp&nbsp&nbsp&nbsp<strong><span id="info2" data-lang="info2">[info2]</span></strong>
			<p id="info3" data-lang="info3">[info3]</p>
			</p>
			<!-- 이미지버튼 삽입 -->	
			</div>
		</footer>
	</div>


    
</body>
</html>
