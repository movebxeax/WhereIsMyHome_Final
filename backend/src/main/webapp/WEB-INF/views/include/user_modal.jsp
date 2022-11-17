<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
	<!-- Login Modal Form -->
	<div class="modal fade" id="loginModalForm" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Login Form -->
				<form action="">
					<div class="modal-header">
						<h5 class="modal-title">로그인</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="Username" class="text-secondary">아이디<span class="text-danger">*</span></label>
							<input type="text" name="userid_login" class="form-control" id="userid_login" placeholder="Username">
						</div>

						<div class="mb-3">
							<label for="Password" class="text-secondary">패스워드<span class="text-danger">*</span></label>
							<input type="password" name="password_login" class="form-control" id="password_login"
								placeholder="Password">
						</div>
						<div class="mb-3">
							<input class="form-check-input" type="checkbox" value="" id="remember" required> <label
								class="form-check-label text-secondary" for="remember">정보 기억하기</label> <a href="#" class="float-end"
								data-bs-toggle="modal" data-bs-target="#passwdResetModalForm">비밀번호
								찾기</a>
						</div>
					</div>
					<div id="loginFailMessageDiv" class="d-flex justify-content-center"></div>
					<div class="modal-footer pt-4">
						<button type="button" class="btn btn-success mx-auto w-100" onclick="javascript:login()">Login</button>
					</div>
					<p class="text-center text-secondary">
						아직 계정이 없으시다면? <a href="#" data-bs-toggle="modal" data-bs-target="#signupModalForm">회원가입</a>
					</p>
				</form>
			</div>
		</div>
	</div>

	<!-- Signup Modal Form -->
	<div class="modal fade" id="signupModalForm" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Signup Form -->
				<form action="">
					<div class="modal-header">
						<h5 class="modal-title">회원가입</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="Username" class="text-secondary">아이디<span class="text-danger">*</span></label>
							<input type="text" name="userid_signup" class="form-control" id="userid_signup" placeholder="Username">
						</div>

						<div class="mb-3">
							<label for="Password" class="text-secondary">패스워드<span class="text-danger">*</span></label>
							<input type="password" name="password_signup" class="form-control" id="password_signup"
								placeholder="Password">
						</div>

						<div class="mb-3">
							<label for="Name" class="text-secondary">이름<span class="text-danger">*</span></label> <input type="text" name="username_signup"
								class="form-control" id="username_signup" placeholder="Name">
						</div>
						<div id="signupFailMessageDiv" class="d-flex justify-content-center"></div>
						<div class="modal-footer pt-4">
							<button type="button" class="btn btn-success mx-auto w-100"
								onclick="javascript:signup()">회원가입</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- User Info Edit Modal Form -->
	<div class="modal fade" id="infoEditModalForm" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- User Info Edit Form -->
				<form action="">
					<div class="modal-header">
						<h5 class="modal-title">정보수정</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="Username" class="text-secondary">아이디<span class="text-danger">*</span></label>
							<input type="text" name="userid_infoedit" class="form-control" id="userid_infoedit" placeholder="Username"
								readonly>
						</div>

						<div class="mb-3">
							<label for="Password" class="text-secondary">패스워드<span class="text-danger">*</span></label>
							<input type="password" name="password_infoedit" class="form-control" id="password_infoedit" placeholder="Password">
						</div>

						<div class="mb-3">
							<label for="Name" class="text-secondary">이름<span class="text-danger">*</span></label>
							<input type="text" name="username_infoedit" class="form-control" id="username_infoedit" placeholder="Name">
						</div>
					</div>
					<div id="infoEditFailMessageDiv" class="d-flex justify-content-center"></div>
					<div class="modal-footer pt-4">
						<button type="button" class="btn btn-success mx-auto w-100" data-bs-dismiss="modal" onclick="javascript:modifyUserInfo()">수정완료</button>
						<button type="button" class="btn btn-danger mx-auto w-100" data-bs-dismiss="modal" onclick="javascript:deleteUserInfo()">회원탈퇴</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<!-- Password Reset Modal Form -->
	<div class="modal fade" id="passwdResetModalForm" tabindex="-1" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered">
			<div class="modal-content">
				<!-- Password Reset Form -->
				<form action="">
					<div class="modal-header">
						<h5 class="modal-title">비밀번호 재설정</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<div class="mb-3">
							<label for="username_reset" class="text-secondary">아이디<span class="text-danger">*</span></label>
							<input type="text" name="username_reset" class="form-control" id="username_reset" placeholder="Username">
						</div>

						<div class="mb-3">
							<label for="name_reset" class="text-secondary">이름<span class="text-danger">*</span></label>
							<input type="text" name="name_reset" class="form-control" id="name_reset" placeholder="Name">
						</div>
					</div>
					<div class="modal-footer pt-4">
						<button type="button" class="btn btn-success mx-auto w-100" data-bs-dismiss="modal">비밀번호 찾기</button>
					</div>
				</form>
			</div>
		</div>
	</div>