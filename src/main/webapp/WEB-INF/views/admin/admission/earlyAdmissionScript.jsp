<%@ page language="java" contentType="text/html; charset=UTF-8"%>

<script>
    $(document).ready(function() {
        // 수시(입시) 정보 조회
        $('.btn-early-admission').click(function() {
            let admissionId = $(this).data('admission-id');         
            $('#earlyAdmissionForm').find('#admissionId').val(admissionId);

            $.ajax({
                url: '/admin/early-admissions/api/' + admissionId,
                type: 'GET',
                contentType : 'application/json; charset=utf-8',
                success: function(response) {
                    if (response.earlyAdmission) {
                        console.log(response);
                        let earlyAdmission = response.earlyAdmission;
                        $('#useCsatReflectedScore option[value="' + earlyAdmission.useCsatReflectedScore + '"]').prop('selected', true).trigger('change');
                        $('#csatReflectedScore').val(earlyAdmission.csatReflectedScore);
                        $('#usePhysicalReflectedScore option[value="' + earlyAdmission.usePhysicalReflectedScore + '"]').prop('selected', true).trigger('change');
                        $('#physicalReflectedScore').val(earlyAdmission.physicalReflectedScore);
                        $('#useInternalReflectedScore option[value="' + earlyAdmission.useInternalReflectedScore + '"]').prop('selected', true).trigger('change');
                        $('#internalReflectedScore').val(earlyAdmission.internalReflectedScore);
                        $('#useInterviewReflectedScore option[value="' + earlyAdmission.useInterviewReflectedScore + '"]').prop('selected', true).trigger('change');
                        $('#interviewReflectedScore').val(earlyAdmission.interviewReflectedScore);
                        $('#acceptedCount').val(earlyAdmission.acceptedCount);
                        $('#earlyAdmissionMemo').val(earlyAdmission.earlyAdmissionMemo);
                    }

                    // 모달 보여주기
                    $('#earlyAdmissionModal').modal('show');
                },
                error: function(xhr, status, error) {
                    console.error(xhr.responseText);
                }
            });
        });

        // 수시(입시) 수능 점수 반영 여부
        $('#useCsatReflectedScore').change(function() {
            if ($(this).val() == 'Y') {
                $('#csatReflectedScore').prop('disabled', false);
            } else {
                $('#csatReflectedScore').prop('disabled', true);
            }
        });

        // 수시(입시) 실기 점수 반영 여부
        $('#usePhysicalReflectedScore').change(function() {
            if ($(this).val() == 'Y') {
                $('#physicalReflectedScore').prop('disabled', false);
            } else {
                $('#physicalReflectedScore').prop('disabled', true);
            }
        });

        // 수시(입시) 내신 점수 반영 여부
        $('#useInternalReflectedScore').change(function() {
            if ($(this).val() == 'Y') {
                $('#internalReflectedScore').prop('disabled', false);
            } else {
                $('#internalReflectedScore').prop('disabled', true);
            }
        });

        // 수시(입시) 면접 점수 반영 여부
        $('#useInterviewReflectedScore').change(function() {
            if ($(this).val() == 'Y') {
                $('#interviewReflectedScore').prop('disabled', false);
            } else {
                $('#interviewReflectedScore').prop('disabled', true);
            }
        });
        
        // 수시(입시) 정보 저장
        $('#earlyAdmissionForm').validate({
            rules: {
                acceptedCount: {
                    required: true
                },
                earlyAdmissionMemo: {
                    maxlength: 500
                },
                // useCsatReflectedScore가 Y인 경우 csatReflectedScore가 필수
                csatReflectedScore: {
                    required: function() {
                        return $('#useCsatReflectedScore').val() == 'Y';
                    }
                },
                // usePhysicalReflectedScore가 Y인 경우 physicalReflectedScore가 필수
                physicalReflectedScore: {
                    required: function() {
                        return $('#usePhysicalReflectedScore').val() == 'Y';
                    }
                },
                // useInternalReflectedScore가 Y인 경우 internalReflectedScore가 필수
                internalReflectedScore: {
                    required: function() {
                        return $('#useInternalReflectedScore').val() == 'Y';
                    }
                },
                // useInterviewReflectedScore가 Y인 경우 interviewReflectedScore가 필수
                interviewReflectedScore: {
                    required: function() {
                        return $('#useInterviewReflectedScore').val() == 'Y';
                    }
                }
            },
            messages: {
                acceptedCount: {
                    required: '모집 인원을 입력하세요.'
                },
                earlyAdmissionMemo: {
                    maxlength: '500자 이하로 입력하세요.'
                },
                csatReflectedScore: {
                    required: '수능 반영 점수를 입력하세요.'
                },
                physicalReflectedScore: {
                    required: '실기 반영 점수를 입력하세요.'
                },
                internalReflectedScore: {
                    required: '내신 반영 점수를 입력하세요.'
                },
                interviewReflectedScore: {
                    required: '면접 반영 점수를 입력하세요.'
                }
            },
            errorClass: 'is-invalid',
            validClass: 'is-valid',
            errorPlacement: function(error, element) {
                error.addClass('invalid-feedback');
                element.closest('.mb-3').append(error);
            },
            submitHandler: function(form) {
                let formData = {
                    admissionId: $('#admissionId').val(),
                    useCsatReflectedScore: $('#useCsatReflectedScore').val(),
                    csatReflectedScore: $('#csatReflectedScore').val(),
                    usePhysicalReflectedScore: $('#usePhysicalReflectedScore').val(),
                    physicalReflectedScore: $('#physicalReflectedScore').val(),
                    useInternalReflectedScore: $('#useInternalReflectedScore').val(),
                    internalReflectedScore: $('#internalReflectedScore').val(),
                    useInterviewReflectedScore: $('#useInterviewReflectedScore').val(),
                    interviewReflectedScore: $('#interviewReflectedScore').val(),
                    acceptedCount: $('#acceptedCount').val(),
                    earlyAdmissionMemo: $('#earlyAdmissionMemo').val()
                };

                $.ajax({
                    url: '/admin/early-admissions/api/' + $('#admissionId').val() + '/update',
                    type: 'POST',
                    data: JSON.stringify(formData),
                    contentType: 'application/json; charset=utf-8',
                    success: function(response) {
                        if (response.result) {
                            $('.btn-early-admission[data-admission-id="' + $('#admissionId').val() + '"]').removeClass('btn-outline-danger').addClass('btn-outline-primary');
                            alert('수시(입시) 정보 저장이 완료되었습니다.');
                        } else {
                            alert('수시(입시) 정보 저장에 실패했습니다.');
                        }
                    },
                    error: function(xhr, status, error) {
                        console.error(xhr.responseText);
                    },
                    complete: function() {
                        // 모달 닫기
                        $('#earlyAdmissionModal').modal('hide');
                    }
                });
            }
        });
    });
</script>