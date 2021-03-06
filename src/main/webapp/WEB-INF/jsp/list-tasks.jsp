<%@ include file="common/header.jspf"%>
<%@ include file="common/navigation.jspf"%>

<div class="container">
	<div>
		<a type="button" class="btn btn-primary btn-md" href="/add-task">Add Task In ToDo List</a>
	</div>
	<br>
	<div class="panel panel-primary">
		<div class="panel-heading">
			<h3>List of Tasks ToDo</h3>
		</div>
		<div class="panel-body">
			<table class="table table-striped">
				<thead>
					<tr>
						<th width="15%">Task Name</th>
						<th width="15%">Description</th>
						<th width="20%">Target Date</th>
						<th width="15%">Status</th>
						<th width="15%">Updated On</th>
						<th width="20%"></th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${tasks}" var="taskInfo">
						<tr>
							<td>${taskInfo.taskName}</td>
							<td>${taskInfo.description}</td>
							<td><fmt:formatDate value="${taskInfo.targetDate}"
									pattern="dd/MM/yyyy" /></td>
							<td>${taskInfo.taskStatus}</td>
							<td>${taskInfo.lastUpdatedDate}</td>
							<td><a type="button" class="btn btn-success"
								href="/update-task?id=${taskInfo.id}">Edit</a>
							<a type="button" class="btn btn-warning"
								href="/delete-task?id=${taskInfo.id}">Remove</a></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

</div>
<%@ include file="common/footer.jspf"%>