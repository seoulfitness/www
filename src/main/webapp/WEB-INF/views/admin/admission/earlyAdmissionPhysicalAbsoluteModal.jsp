<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- 절대평가 점수 입력 모달 --%>
<form id="earlyAdmissionPhysicalAbsoluteScoreForm">
    <div class="modal fade modal-lg" id="earlyAdmissionPhysicalAbsoluteScoreModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title early-admission-physical-absolute-score-modal-title" id="staticBackdropLabel">절대평가 점수 입력</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>            
                <div class="modal-body">
                    <!-- 안내 -->
                    <div class="mb-3">
                        <div class="alert alert-info">
                            <div class="mb-1">등급별 점수 및 기록은 등급 사용인 경우에만 입력 가능합니다.</div>
                            <div class="mb-1">범위가 없는 단일 기록일 경우 최소 기록과 최대 기록을 동일하게 입력합니다.</div>
                        </div>
                    </div>
                    <!--// 안내 -->
                
                    <%-- 1등급 --%>
                    <div class="mb-3 row">    
                        <div class="col-md-3">
                            <label class="small mb-1" for="useGrade1">1등급 사용 여부 <span class="text-danger">*</span></label>
                            <select class="form-control" id="useGrade1" name="useGrade1">
                                <option value="Y" selected>사용</option>
                            </select>
                        </div>
                        <div class="col-md-3">
                            <label class="small mb-1" for="grade1Score">1등급 점수 <span class="text-danger">*</span></label>
                            <input class="form-control" id="grade1Score" name="grade1Score" type="text" value="" />
                        </div>  
                        <div class="col-md-6">
                            <label class="small mb-1" for="grade1RecordMin">1등급 기록<span class="text-danger">*</span></label>
                            <div class="input-group">
                                <input class="form-control col-auto" id="grade1RecordMin" name="grade1RecordMin" type="text" value="" />
                                <span class="input-group-text">~</span>
                                <input class="form-control col-auto" id="grade1RecordMax" name="grade1RecordMax" type="text" value="" />
                            </div>
                        </div>        
                    </div>
                    <%--// 1등급 --%>

                    <%-- 2등급 ~ 40등급 --%>
                    <c:forEach var="i" begin="2" end="40">
                        <div class="mb-3 row">    
                            <div class="col-md-3">
                                <label class="small mb-1" for="useGrade${i}">${i}등급 사용 여부 <span class="text-danger">*</span></label>
                                <select class="form-control" id="useGrade${i}" name="useGrade${i}">
                                    <option value="Y" selected>사용</option>
                                    <option value="N">사용안함</option>
                                </select>
                            </div>
                            <div class="col-md-3">
                                <label class="small mb-1" for="grade${i}Score">${i}등급 점수 <span class="text-danger">*</span></label>
                                <input class="form-control" id="grade${i}Score" name="grade${i}Score" type="text" value="" />
                            </div>  
                            <div class="col-md-6">
                                <label class="small mb-1" for="grade${i}RecordMin">${i}등급 기록<span class="text-danger">*</span></label>
                                <div class="input-group">
                                    <input class="form-control col-auto" id="grade${i}RecordMin" name="grade${i}RecordMin" type="text" value="" />
                                    <span class="input-group-text">~</span>
                                    <input class="form-control col-auto" id="grade${i}RecordMax" name="grade${i}RecordMax" type="text" value="" />
                                </div>
                            </div>        
                        </div>  
                    </c:forEach>
                    <%--// 2등급 ~ 40등급 --%>    
                    <div class="mb-3">
                        <label for="earlyAdmissionPhysicalAbsoluteMemo" class="form-label">메모</label>
                        <textarea class="form-control" id="earlyAdmissionPhysicalAbsoluteMemo" name="earlyAdmissionPhysicalAbsoluteMemo" placeholder="메모"></textarea>
                    </div>      
                </div>
                <div class="modal-footer">
                    <button type="button" id="saveAbsoluteScore" class="btn btn-primary">저장</button>
                    <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
                </div>            
            </div>
        </div>
    </div>
</form>
<%--// 절대평가 점수 입력 모달 --%>
