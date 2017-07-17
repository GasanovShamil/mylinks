<%@page import="javax.script.ScriptContext"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
	.table-responsive>.table>tbody>tr>td {
	    vertical-align: middle;
	}
	.green {
	    color: green;
	}
	.red {
	    color: red;
	}
</style>
<%@ include file="header.jsp"%>
<br>
<div class="container-fluid table-responsive">
	<div class="row">
		<table class="table table-striped" id="urlTable">
			<thead>
				<tr>
					<th style="vertical-align:middle!important; width: 6%">ShortUrl</th>
					<th style="vertical-align:middle; width: 50%">LongUrl</th>
					<th style="vertical-align:middle; width: 8%">Created</th>
					<th style="vertical-align:middle; width: 8%">Start</th>
					<th style="vertical-align:middle; width: 8%">Expire</th>
					<th style="vertical-align:middle; width: 8%">Password</th>
					<th style="vertical-align:middle; width: 4%">Clicks</th>
					<th style="vertical-align:middle; width: 4%">Captcha</th>
					<th style="vertical-align:middle; width: 4%">Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="url" items="${urlList}">
					<tr id="row-${url.getShortUrl()}">
						<td>${url.getShortUrl()}</td>
						<td><a href="${url.getLongUrl()}">${url.getLongUrl()}</a></td>
						<td>${url.getCreateDate()}</td>
						<td class="start">${url.getStartDate()}</td>
						<td class="end">${url.getExpireDate()}</td>
						<td class="password">${url.getPassword()}</td> 
						<td>${url.getNbClicks()}/<span class="clicks">${url.getTotalClicks()}</span></td>
						<td class="captcha">${url.getCaptchaIcon()}</td>
						<td>
							<a href="${url.getShortUrl()}~s" class="btn btn-warning"><span class="glyphicon glyphicon-stats"></span></a>
							<button class="btn btn-info edit-url"
										data-url="${url.getShortUrl()}"
										data-start="${url.getStartDate()}"
										data-end="${url.getExpireDate()}"
										data-password="${url.getPassword()}"
										data-clicks="${url.getTotalClicks()}"
										data-captcha="${url.isCaptcha()}">
								<span class="glyphicon glyphicon-edit"></span>
							</button>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>

<div id="edit-url" class="modal fade" tabindex="-1" role="dialog">
	<div class="modal-dialog modal-sm" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="title">
					<span>Modifier</span>
				</h4>
			</div>
			<div class="modal-body">
				<input type="hidden" id="edit-url" class="form-control" />
				<div class="">
				<label for="edit-url-short">From</label>
					<input type="date" id="edit-start" class="form-control" />
				</div>
				<div class="">
					<label for="edit-url-short">Until</label>
					<input type="date" id="edit-end" class="form-control" />
				</div>
				<div class="">
					<label for="edit-url-short">Password</label>
					<input type="text" id="edit-password" class="form-control" />
				</div>
				<div class="">
					<label for="edit-url-short">Clicks</label>
					<input type="number" id="edit-clicks" class="form-control" />
				</div>
				<div class="">
					<label for="edit-url-short">Captcha</label>
					<input type="checkbox" id="edit-captcha" class="form-control" />
				</div>
				<div class="text-center">
					<button id="edit-submit" class="btn btn-primary">Modifier</button>
				</div>
			</div>
		</div>
	</div>
</div>
</body>
<script>
$(document).ready(function() {
    $('#urlTable').DataTable();
} );
</script>
</html>