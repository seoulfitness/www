<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<div class="modal fade" id="earlyAdmissionModal" data-bs-backdrop="static" tabindex="-1">
    <div class="modal-dialog modal-dialog-scrollable">
        <div class="modal-content">
            <form id="earlyAdmissionForm">
                <input type="hidden" id="admissionId" name="admissionId">
                <div class="modal-header">
                    <h1 class="modal-title">수시(입시) 정보</h1>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div class="modal-body" style="max-height: 70vh; overflow-y: auto;">
                    <div class="mb-3">
                        <label for="useCsatReflectedScore" class="form-label">수능 점수 반영</label>
                        <select class="form-select" id="useCsatReflectedScore" name="useCsatReflectedScore">
                            <option value="Y">반영</option>
                            <option value="N">미반영</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="csatReflectedScore" class="form-label">수능 반영 점수</label>
                        <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="0.00" id="csatReflectedScore" name="csatReflectedScore" placeholder="수능 반영 점수">
                    </div>
                    <div class="mb-3">
                        <label for="usePhysicalReflectedScore" class="form-label">실기 점수 반영</label>
                        <select class="form-select" id="usePhysicalReflectedScore" name="usePhysicalReflectedScore">
                            <option value="Y">반영</option>
                            <option value="N">미반영</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="physicalReflectedScore" class="form-label">실기 반영 점수</label>
                        <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="0.00" id="physicalReflectedScore" name="physicalReflectedScore" placeholder="실기 반영 점수">
                    </div>
                    <div class="mb-3">
                        <label for="useInternalReflectedScore" class="form-label">내신 점수 반영</label>
                        <select class="form-select" id="useInternalReflectedScore" name="useInternalReflectedScore">
                            <option value="Y">반영</option>
                            <option value="N">미반영</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="internalReflectedScore" class="form-label">내신 반영 점수</label>
                        <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="0.00" id="internalReflectedScore" name="internalReflectedScore" placeholder="내신 반영 점수">
                    </div>
                    <div class="mb-3">
                        <label for="useInterviewReflectedScore" class="form-label">면접 점수 반영</label>
                        <select class="form-select" id="useInterviewReflectedScore" name="useInterviewReflectedScore">
                            <option value="Y">반영</option>
                            <option value="N">미반영</option>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="interviewReflectedScore" class="form-label">면접 반영 점수</label>
                        <input type="number" class="form-control" min="0.00" max="1000.00" step="0.01" value="0.00" id="interviewReflectedScore" name="interviewReflectedScore" placeholder="면접 반영 점수">
                    </div>
                    <div class="mb-3">
                        <label for="acceptedCount" class="form-label">모집 인원</label>
                        <input type="text" class="form-control" id="acceptedCount" name="acceptedCount" placeholder="모집 인원">
                    </div>
                    <div class="mb-3">
                        <label for="earlyAdmissionMemo" class="form-label">메모</label>
                        <textarea class="form-control" id="earlyAdmissionMemo" name="earlyAdmissionMemo" placeholder="메모"></textarea>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-outline-secondary" data-bs-dismiss="modal">닫기</button>
                    <button type="submit" class="btn btn-primary">저장</button>
                </div>
            </form>
        </div>
    </div>
</div>