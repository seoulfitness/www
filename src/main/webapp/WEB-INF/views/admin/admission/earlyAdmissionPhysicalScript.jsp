<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    $(document).ready(function() {
        // 상수 정의
        const FORM_ID = '#earlyAdmissionPhysicalAbsoluteScoreForm';
        const MODAL_ID = '#earlyAdmissionPhysicalAbsoluteScoreModal';
        const SAVE_BUTTON_ID = '#saveAbsoluteScore';
        
        let id = '';
        let admissionId = '';
        let earlyAdmissionPhysicalId = '';
        let earlyAdmissionPhysicalSubjectId = '';
        let earlyAdmissionPhysicalAbsoluteId = '';
        let earlyAdmissionPhysicalWomanAbsoluteId = '';
        let subjectName = '';
        let gender = '';
        let action = '';

        // 유틸리티 함수
        const getFormData = ($form) => {
            const formData = {};
            
            // form 데이터를 객체로 변환
            const formArray = $form.serializeArray();
            formArray.forEach(item => {
                formData[item.name] = item.value;
            });

            // 등급 사용 여부 값 추가
            for (let i = 1; i <= 40; i++) {
                formData['useGrade' + i] = $('#useGrade' + i).val() || 'N';
            }

            const additionalData = {
                admissionId,
                earlyAdmissionPhysicalId,
                earlyAdmissionPhysicalSubjectId
            };

            // action이 create가 아닐 때만 ID 값을 포함
            if (action != 'create') {
                additionalData[gender == 'man' ? 'earlyAdmissionPhysicalAbsoluteId' : 'earlyAdmissionPhysicalWomanAbsoluteId'] = 
                    gender == 'man' ? earlyAdmissionPhysicalAbsoluteId : earlyAdmissionPhysicalWomanAbsoluteId;
            }

            return {
                ...formData,
                ...additionalData
            };
        };       

        const updateButtonState = (id, action, gender) => {
            $('.btn-' + gender + '-absolute-score[data-id="' + id + '"]')
                .removeClass('btn-outline-danger')
                .addClass('btn-primary')
                .text('배점 보기');
        };

        // 절대평가 점수 버튼 클릭 이벤트
        $('.btn-absolute-score').click(function(e) {
            e.preventDefault();
            
            // 데이터 초기화
            id = $(this).data('id');
            admissionId = $(this).data('admission-id');
            earlyAdmissionPhysicalId = $(this).data('early-admission-physical-id');
            earlyAdmissionPhysicalSubjectId = $(this).data('subject-id');
            earlyAdmissionPhysicalAbsoluteId = $(this).data('early-admission-physical-absolute-id');
            subjectName = $(this).data('subject-name');
            gender = $(this).data('gender');
            action = $(this).data('action');

            // action이 create일 경우 모든 등급의 점수와 기록을 0.0000으로 초기화
            if (action == 'create') {
                // 1등급 초기화: 사용함, 입력 가능
                $('#useGrade1').val('Y');
                $('#grade1Score, #grade1RecordMin, #grade1RecordMax')
                    .val('0.0000')
                    .prop('disabled', false);

                // 2-40등급 초기화: 사용안함, 입력 불가
                for (let i = 2; i <= 40; i++) {
                    $('#useGrade' + i).val('N').prop('disabled', true);
                    $('#grade' + i + 'Score, #grade' + i + 'RecordMin, #grade' + i + 'RecordMax')
                        .val('0.0000')
                        .prop('disabled', true);
                }

                // 2등급 사용 여부 설정
                $('#useGrade2').val('N').prop('disabled', false);
            } else {
                let url = '/admin/earlyAdmissionPhysical' + (gender == 'man' ? 'Man' : 'Woman') + 'Absolute/' + earlyAdmissionPhysicalAbsoluteId;
                // 점수 불러오기
                $.ajax({
                    url: url,
                    type: 'GET',
                    success: (response) => {
                        const data = response.earlyAdmissionPhysicalAbsolute;

                        // 1등급 ~ 40등급 사용 여부 설정
                        for (let i = 1; i <= 40; i++) {
                            $('#useGrade' + i).val(data['useGrade' + i] || 'N').prop('disabled', !data['useGrade' + i]);
                        }

                        // 1등급 ~ 40등급 점수 불러오기
                        for (let i = 1; i <= 40; i++) {
                            $('#grade' + i + 'Score').val(data['grade' + i + 'Score'] || '0.0000');
                            $('#grade' + i + 'RecordMin').val(data['grade' + i + 'RecordMin'] || '0.0000');
                            $('#grade' + i + 'RecordMax').val(data['grade' + i + 'RecordMax'] || '0.0000');
                        }

                        // 1등급부터 40등급까지 사용여부 확인한 후 사용 여부 설정
                        for (let i = 1; i <= 40; i++) {
                            if (data['useGrade' + i] == 'Y') {
                                $('#grade' + i + 'Score').val(data['grade' + i + 'Score'] || '0.0000').prop('disabled', false);
                                $('#grade' + i + 'RecordMin').val(data['grade' + i + 'RecordMin'] || '0.0000').prop('disabled', false);
                                $('#grade' + i + 'RecordMax').val(data['grade' + i + 'RecordMax'] || '0.0000').prop('disabled', false);
                                $('#useGrade' + (i + 1)).prop('disabled', false);
                            } else {
                                $('#grade' + i + 'Score').val('0.0000').prop('disabled', true);
                                $('#grade' + i + 'RecordMin').val('0.0000').prop('disabled', true);
                                $('#grade' + i + 'RecordMax').val('0.0000').prop('disabled', true);
                                $('#useGrade' + (i + 1)).prop('disabled', true);
                            }
                        }

                        // 메모 불러오기
                        $('#earlyAdmissionPhysicalAbsoluteMemo').val(data.earlyAdmissionPhysicalAbsoluteMemo || '');
                    },
                    error: (xhr, status, error) => {
                        alert('점수 정보를 불러오는 중 오류가 발생했습니다. 다시 시도해주세요.');
                        console.error('Error:', error);
                    }
                });
            }

            // 모달 제목 설정
            const genderText = gender == 'man' ? '남자' : '여자';
            const modalTitle = subjectName + ' ' + genderText + ' 절대평가 점수';
            $('.early-admission-physical-absolute-score-modal-title').text(modalTitle);

            // 모달 표시
            $(MODAL_ID).modal('show');
        });

        // 폼 유효성 검사 설정
        const $form = $(FORM_ID);
        const validator = $form.validate({
            rules: {
                grade1Score: {
                    required: true,
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade1RecordMin: {
                    required: true,
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade1RecordMax: {
                    required: true,
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade2Score: {
                    required: function() {
                        return $('#useGrade2').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade2RecordMin: {
                    required: function() {
                        return $('#useGrade2').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade2RecordMax: {
                    required: function() {
                        return $('#useGrade2').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade3Score: {
                    required: function() {
                        return $('#useGrade3').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade3RecordMin: {
                    required: function() {
                        return $('#useGrade3').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade3RecordMax: {
                    required: function() {
                        return $('#useGrade3').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade4Score: {
                    required: function() {
                        return $('#useGrade4').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade4RecordMin: {
                    required: function() {
                        return $('#useGrade4').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade4RecordMax: {
                    required: function() {
                        return $('#useGrade4').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade5Score: {
                    required: function() {
                        return $('#useGrade5').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade5RecordMin: {
                    required: function() {
                        return $('#useGrade5').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade5RecordMax: {
                    required: function() {
                        return $('#useGrade5').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade6Score: {
                    required: function() {
                        return $('#useGrade6').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade6RecordMin: {
                    required: function() {
                        return $('#useGrade6').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade6RecordMax: {
                    required: function() {
                        return $('#useGrade6').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade7Score: {
                    required: function() {
                        return $('#useGrade7').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade7RecordMin: {
                    required: function() {
                        return $('#useGrade7').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade7RecordMax: {
                    required: function() {
                        return $('#useGrade7').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade8Score: {
                    required: function() {
                        return $('#useGrade8').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade8RecordMin: {
                    required: function() {
                        return $('#useGrade8').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade8RecordMax: {
                    required: function() {
                        return $('#useGrade8').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade9Score: {
                    required: function() {
                        return $('#useGrade9').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade9RecordMin: {
                    required: function() {
                        return $('#useGrade9').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade9RecordMax: {
                    required: function() {
                        return $('#useGrade9').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade10Score: {
                    required: function() {
                        return $('#useGrade10').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade10RecordMin: {
                    required: function() {
                        return $('#useGrade10').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade10RecordMax: {
                    required: function() {
                        return $('#useGrade10').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade11Score: {
                    required: function() {
                        return $('#useGrade11').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade11RecordMin: {
                    required: function() {
                        return $('#useGrade11').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade11RecordMax: {
                    required: function() {
                        return $('#useGrade11').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade12Score: {
                    required: function() {
                        return $('#useGrade12').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade12RecordMin: {
                    required: function() {
                        return $('#useGrade12').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade12RecordMax: {
                    required: function() {
                        return $('#useGrade12').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade13Score: {
                    required: function() {
                        return $('#useGrade13').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade13RecordMin: {
                    required: function() {
                        return $('#useGrade13').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade13RecordMax: {
                    required: function() {
                        return $('#useGrade13').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade14Score: {
                    required: function() {
                        return $('#useGrade14').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade14RecordMin: {
                    required: function() {
                        return $('#useGrade14').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade14RecordMax: {
                    required: function() {
                        return $('#useGrade14').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade15Score: {
                    required: function() {
                        return $('#useGrade15').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade15RecordMin: {
                    required: function() {
                        return $('#useGrade15').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade15RecordMax: {
                    required: function() {
                        return $('#useGrade15').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade16Score: {
                    required: function() {
                        return $('#useGrade16').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade16RecordMin: {
                    required: function() {
                        return $('#useGrade16').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade16RecordMax: {
                    required: function() {
                        return $('#useGrade16').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade17Score: {
                    required: function() {
                        return $('#useGrade17').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade17RecordMin: {
                    required: function() {
                        return $('#useGrade17').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade17RecordMax: {
                    required: function() {
                        return $('#useGrade17').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade18Score: {
                    required: function() {
                        return $('#useGrade18').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade18RecordMin: {
                    required: function() {
                        return $('#useGrade18').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade18RecordMax: {
                    required: function() {
                        return $('#useGrade18').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade19Score: {
                    required: function() {
                        return $('#useGrade19').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade19RecordMin: {
                    required: function() {
                        return $('#useGrade19').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade19RecordMax: {
                    required: function() {
                        return $('#useGrade19').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 1000.0000
                },
                grade20Score: {
                    required: function() {
                        return $('#useGrade20').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade20RecordMin: {
                    required: '20등급 기록 최소값을 입력하세요.',
                    number: '20등급 기록 최소값은 숫자로 입력하세요.',
                    min: '20등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '20등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade20RecordMax: {
                    required: '20등급 기록 최대값을 입력하세요.',
                    number: '20등급 기록 최대값은 숫자로 입력하세요.',
                    min: '20등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '20등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade21Score: {
                    required: '21등급 점수를 입력하세요.',
                    number: '21등급 점수는 숫자로 입력하세요.',
                    min: '21등급 점수는 0 이상이어야 합니다.',
                    max: '21등급 점수는 100.0000 이하여야 합니다.'
                },
                grade21RecordMin: {
                    required: '21등급 기록 최소값을 입력하세요.',
                    number: '21등급 기록 최소값은 숫자로 입력하세요.',
                    min: '21등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '21등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade21RecordMax: {
                    required: '21등급 기록 최대값을 입력하세요.',
                    number: '21등급 기록 최대값은 숫자로 입력하세요.',
                    min: '21등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '21등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade22Score: {
                    required: '22등급 점수를 입력하세요.',
                    number: '22등급 점수는 숫자로 입력하세요.',
                    min: '22등급 점수는 0 이상이어야 합니다.',
                    max: '22등급 점수는 100.0000 이하여야 합니다.'
                },
                grade22RecordMin: {
                    required: '22등급 기록 최소값을 입력하세요.',
                    number: '22등급 기록 최소값은 숫자로 입력하세요.',
                    min: '22등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '22등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade22RecordMax: {
                    required: '22등급 기록 최대값을 입력하세요.',
                    number: '22등급 기록 최대값은 숫자로 입력하세요.',
                    min: '22등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '22등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade23Score: {
                    required: '23등급 점수를 입력하세요.',
                    number: '23등급 점수는 숫자로 입력하세요.',
                    min: '23등급 점수는 0 이상이어야 합니다.',
                    max: '23등급 점수는 100.0000 이하여야 합니다.'
                },
                grade23RecordMin: {
                    required: '23등급 기록 최소값을 입력하세요.',
                    number: '23등급 기록 최소값은 숫자로 입력하세요.',
                    min: '23등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '23등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade23RecordMax: {
                    required: '23등급 기록 최대값을 입력하세요.',
                    number: '23등급 기록 최대값은 숫자로 입력하세요.',
                    min: '23등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '23등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade24Score: {
                    required: '24등급 점수를 입력하세요.',
                    number: '24등급 점수는 숫자로 입력하세요.',
                    min: '24등급 점수는 0 이상이어야 합니다.',
                    max: '24등급 점수는 100.0000 이하여야 합니다.'
                },
                grade24RecordMin: {
                    required: '24등급 기록 최소값을 입력하세요.',
                    number: '24등급 기록 최소값은 숫자로 입력하세요.',
                    min: '24등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '24등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade24RecordMax: {
                    required: '24등급 기록 최대값을 입력하세요.',
                    number: '24등급 기록 최대값은 숫자로 입력하세요.',
                    min: '24등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '24등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade25Score: {
                    required: '25등급 점수를 입력하세요.',
                    number: '25등급 점수는 숫자로 입력하세요.',
                    min: '25등급 점수는 0 이상이어야 합니다.',
                    max: '25등급 점수는 100.0000 이하여야 합니다.'
                },
                grade25RecordMin: {
                    required: '25등급 기록 최소값을 입력하세요.',
                    number: '25등급 기록 최소값은 숫자로 입력하세요.',
                    min: '25등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '25등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade25RecordMax: {
                    required: '25등급 기록 최대값을 입력하세요.',
                    number: '25등급 기록 최대값은 숫자로 입력하세요.',
                    min: '25등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '25등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade26Score: {
                    required: '26등급 점수를 입력하세요.',
                    number: '26등급 점수는 숫자로 입력하세요.',
                    min: '26등급 점수는 0 이상이어야 합니다.',
                    max: '26등급 점수는 100.0000 이하여야 합니다.'
                },
                grade26RecordMin: {
                    required: '26등급 기록 최소값을 입력하세요.',
                    number: '26등급 기록 최소값은 숫자로 입력하세요.',
                    min: '26등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '26등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade26RecordMax: {
                    required: '26등급 기록 최대값을 입력하세요.',
                    number: '26등급 기록 최대값은 숫자로 입력하세요.',
                    min: '26등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '26등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade27Score: {
                    required: '27등급 점수를 입력하세요.',
                    number: '27등급 점수는 숫자로 입력하세요.',
                    min: '27등급 점수는 0 이상이어야 합니다.',
                    max: '27등급 점수는 100.0000 이하여야 합니다.'
                },
                grade27RecordMin: {
                    required: '27등급 기록 최소값을 입력하세요.',
                    number: '27등급 기록 최소값은 숫자로 입력하세요.',
                    min: '27등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '27등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade27RecordMax: {
                    required: '27등급 기록 최대값을 입력하세요.',
                    number: '27등급 기록 최대값은 숫자로 입력하세요.',
                    min: '27등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '27등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade28Score: {
                    required: '28등급 점수를 입력하세요.',
                    number: '28등급 점수는 숫자로 입력하세요.',
                    min: '28등급 점수는 0 이상이어야 합니다.',
                    max: '28등급 점수는 100.0000 이하여야 합니다.'
                },
                grade28RecordMin: {
                    required: '28등급 기록 최소값을 입력하세요.',
                    number: '28등급 기록 최소값은 숫자로 입력하세요.',
                    min: '28등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '28등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade28RecordMax: {
                    required: '28등급 기록 최대값을 입력하세요.',
                    number: '28등급 기록 최대값은 숫자로 입력하세요.',
                    min: '28등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '28등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade29Score: {
                    required: '29등급 점수를 입력하세요.',
                    number: '29등급 점수는 숫자로 입력하세요.',
                    min: '29등급 점수는 0 이상이어야 합니다.',
                    max: '29등급 점수는 100.0000 이하여야 합니다.'
                },
                grade29RecordMin: {
                    required: '29등급 기록 최소값을 입력하세요.',
                    number: '29등급 기록 최소값은 숫자로 입력하세요.',
                    min: '29등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '29등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade29RecordMax: {
                    required: '29등급 기록 최대값을 입력하세요.',
                    number: '29등급 기록 최대값은 숫자로 입력하세요.',
                    min: '29등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '29등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade30Score: {
                    required: '30등급 점수를 입력하세요.',
                    number: '30등급 점수는 숫자로 입력하세요.',
                    min: '30등급 점수는 0 이상이어야 합니다.',
                    max: '30등급 점수는 100.0000 이하여야 합니다.'
                },
                grade30RecordMin: {
                    required: '30등급 기록 최소값을 입력하세요.',
                    number: '30등급 기록 최소값은 숫자로 입력하세요.',
                    min: '30등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '30등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade30RecordMax: {
                    required: '30등급 기록 최대값을 입력하세요.',
                    number: '30등급 기록 최대값은 숫자로 입력하세요.',
                    min: '30등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '30등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade31Score: {
                    required: '31등급 점수를 입력하세요.',
                    number: '31등급 점수는 숫자로 입력하세요.',
                    min: '31등급 점수는 0 이상이어야 합니다.',
                    max: '31등급 점수는 100.0000 이하여야 합니다.'
                },
                grade31RecordMin: {
                    required: '31등급 기록 최소값을 입력하세요.',
                    number: '31등급 기록 최소값은 숫자로 입력하세요.',
                    min: '31등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '31등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade31RecordMax: {
                    required: '31등급 기록 최대값을 입력하세요.',
                    number: '31등급 기록 최대값은 숫자로 입력하세요.',
                    min: '31등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '31등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade32Score: {
                    required: '32등급 점수를 입력하세요.',
                    number: '32등급 점수는 숫자로 입력하세요.',
                    min: '32등급 점수는 0 이상이어야 합니다.',
                    max: '32등급 점수는 100.0000 이하여야 합니다.'
                },
                grade32RecordMin: {
                    required: '32등급 기록 최소값을 입력하세요.',
                    number: '32등급 기록 최소값은 숫자로 입력하세요.',
                    min: '32등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '32등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade32RecordMax: {
                    required: '32등급 기록 최대값을 입력하세요.',
                    number: '32등급 기록 최대값은 숫자로 입력하세요.',
                    min: '32등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '32등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade33Score: {
                    required: '33등급 점수를 입력하세요.',
                    number: '33등급 점수는 숫자로 입력하세요.',
                    min: '33등급 점수는 0 이상이어야 합니다.',
                    max: '33등급 점수는 100.0000 이하여야 합니다.'
                },
                grade33RecordMin: {
                    required: '33등급 기록 최소값을 입력하세요.',
                    number: '33등급 기록 최소값은 숫자로 입력하세요.',
                    min: '33등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '33등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade33RecordMax: {
                    required: '33등급 기록 최대값을 입력하세요.',
                    number: '33등급 기록 최대값은 숫자로 입력하세요.',
                    min: '33등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '33등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade34Score: {
                    required: '34등급 점수를 입력하세요.',
                    number: '34등급 점수는 숫자로 입력하세요.',
                    min: '34등급 점수는 0 이상이어야 합니다.',
                    max: '34등급 점수는 100.0000 이하여야 합니다.'
                },
                grade34RecordMin: {
                    required: '34등급 기록 최소값을 입력하세요.',
                    number: '34등급 기록 최소값은 숫자로 입력하세요.',
                    min: '34등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '34등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade34RecordMax: {
                    required: '34등급 기록 최대값을 입력하세요.',
                    number: '34등급 기록 최대값은 숫자로 입력하세요.',
                    min: '34등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '34등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade35Score: {
                    required: '35등급 점수를 입력하세요.',
                    number: '35등급 점수는 숫자로 입력하세요.',
                    min: '35등급 점수는 0 이상이어야 합니다.',
                    max: '35등급 점수는 100.0000 이하여야 합니다.'
                },
                grade35RecordMin: {
                    required: '35등급 기록 최소값을 입력하세요.',
                    number: '35등급 기록 최소값은 숫자로 입력하세요.',
                    min: '35등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '35등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade35RecordMax: {
                    required: '35등급 기록 최대값을 입력하세요.',
                    number: '35등급 기록 최대값은 숫자로 입력하세요.',
                    min: '35등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '35등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade36Score: {
                    required: '36등급 점수를 입력하세요.',
                    number: '36등급 점수는 숫자로 입력하세요.',
                    min: '36등급 점수는 0 이상이어야 합니다.',
                    max: '36등급 점수는 100.0000 이하여야 합니다.'
                },
                grade36RecordMin: {
                    required: '36등급 기록 최소값을 입력하세요.',
                    number: '36등급 기록 최소값은 숫자로 입력하세요.',
                    min: '36등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '36등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade36RecordMax: {
                    required: '36등급 기록 최대값을 입력하세요.',
                    number: '36등급 기록 최대값은 숫자로 입력하세요.',
                    min: '36등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '36등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade37Score: {
                    required: '37등급 점수를 입력하세요.',
                    number: '37등급 점수는 숫자로 입력하세요.',
                    min: '37등급 점수는 0 이상이어야 합니다.',
                    max: '37등급 점수는 100.0000 이하여야 합니다.'
                },
                grade37RecordMin: {
                    required: '37등급 기록 최소값을 입력하세요.',
                    number: '37등급 기록 최소값은 숫자로 입력하세요.',
                    min: '37등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '37등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade37RecordMax: {
                    required: '37등급 기록 최대값을 입력하세요.',
                    number: '37등급 기록 최대값은 숫자로 입력하세요.',
                    min: '37등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '37등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade38Score: {
                    required: '38등급 점수를 입력하세요.',
                    number: '38등급 점수는 숫자로 입력하세요.',
                    min: '38등급 점수는 0 이상이어야 합니다.',
                    max: '38등급 점수는 100.0000 이하여야 합니다.'
                },
                grade38RecordMin: {
                    required: '38등급 기록 최소값을 입력하세요.',
                    number: '38등급 기록 최소값은 숫자로 입력하세요.',
                    min: '38등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '38등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade38RecordMax: {
                    required: '38등급 기록 최대값을 입력하세요.',
                    number: '38등급 기록 최대값은 숫자로 입력하세요.',
                    min: '38등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '38등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade39Score: {
                    required: '39등급 점수를 입력하세요.',
                    number: '39등급 점수는 숫자로 입력하세요.',
                    min: '39등급 점수는 0 이상이어야 합니다.',
                    max: '39등급 점수는 100.0000 이하여야 합니다.'
                },
                grade39RecordMin: {
                    required: '39등급 기록 최소값을 입력하세요.',
                    number: '39등급 기록 최소값은 숫자로 입력하세요.',
                    min: '39등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '39등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade39RecordMax: {
                    required: '39등급 기록 최대값을 입력하세요.',
                    number: '39등급 기록 최대값은 숫자로 입력하세요.',
                    min: '39등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '39등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade40Score: {
                    required: function() {
                        return $('#useGrade40').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade40RecordMin: {
                    required: '40등급 기록 최소값을 입력하세요.',
                    number: '40등급 기록 최소값은 숫자로 입력하세요.',
                    min: '40등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '40등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade40RecordMax: {
                    required: '40등급 기록 최대값을 입력하세요.',
                    number: '40등급 기록 최대값은 숫자로 입력하세요.',
                    min: '40등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '40등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                earlyAdmissionPhysicalAbsoluteMemo: {
                    maxlength: 500
                },
            },
            messages: {
                grade1Score: {
                    required: '1등급 점수를 입력하세요.',
                    number: '1등급 점수는 숫자로 입력하세요.',
                    min: '1등급 점수는 0 이상이어야 합니다.',
                    max: '1등급 점수는 100.0000 이하여야 합니다.'
                },
                grade1RecordMin: {
                    required: '1등급 기록 최소값을 입력하세요.',
                    number: '1등급 기록 최소값은 숫자로 입력하세요.',
                    min: '1등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '1등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade1RecordMax: {
                    required: '1등급 기록 최대값을 입력하세요.',
                    number: '1등급 기록 최대값은 숫자로 입력하세요.',
                    min: '1등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '1등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade2Score: {
                    required: '2등급 점수를 입력하세요.',
                    number: '2등급 점수는 숫자로 입력하세요.',
                    min: '2등급 점수는 0 이상이어야 합니다.',
                    max: '2등급 점수는 100.0000 이하여야 합니다.'
                },
                grade2RecordMin: {
                    required: '2등급 기록 최소값을 입력하세요.',
                    number: '2등급 기록 최소값은 숫자로 입력하세요.',
                    min: '2등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '2등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade2RecordMax: {
                    required: '2등급 기록 최대값을 입력하세요.',
                    number: '2등급 기록 최대값은 숫자로 입력하세요.',
                    min: '2등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '2등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade3Score: {
                    required: '3등급 점수를 입력하세요.',
                    number: '3등급 점수는 숫자로 입력하세요.',
                    min: '3등급 점수는 0 이상이어야 합니다.',
                    max: '3등급 점수는 100.0000 이하여야 합니다.'
                },
                grade3RecordMin: {
                    required: '3등급 기록 최소값을 입력하세요.',
                    number: '3등급 기록 최소값은 숫자로 입력하세요.',
                    min: '3등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '3등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade3RecordMax: {
                    required: '3등급 기록 최대값을 입력하세요.',
                    number: '3등급 기록 최대값은 숫자로 입력하세요.',
                    min: '3등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '3등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade4Score: {
                    required: '4등급 점수를 입력하세요.',
                    number: '4등급 점수는 숫자로 입력하세요.',
                    min: '4등급 점수는 0 이상이어야 합니다.',
                    max: '4등급 점수는 100.0000 이하여야 합니다.'
                },
                grade4RecordMin: {
                    required: '4등급 기록 최소값을 입력하세요.',
                    number: '4등급 기록 최소값은 숫자로 입력하세요.',
                    min: '4등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '4등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade4RecordMax: {
                    required: '4등급 기록 최대값을 입력하세요.',
                    number: '4등급 기록 최대값은 숫자로 입력하세요.',
                    min: '4등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '4등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade5Score: {
                    required: '5등급 점수를 입력하세요.',
                    number: '5등급 점수는 숫자로 입력하세요.',
                    min: '5등급 점수는 0 이상이어야 합니다.',
                    max: '5등급 점수는 100.0000 이하여야 합니다.'
                },
                grade5RecordMin: {
                    required: '5등급 기록 최소값을 입력하세요.',
                    number: '5등급 기록 최소값은 숫자로 입력하세요.',
                    min: '5등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '5등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade5RecordMax: {
                    required: '5등급 기록 최대값을 입력하세요.',
                    number: '5등급 기록 최대값은 숫자로 입력하세요.',
                    min: '5등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '5등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade6Score: {
                    required: '6등급 점수를 입력하세요.',
                    number: '6등급 점수는 숫자로 입력하세요.',
                    min: '6등급 점수는 0 이상이어야 합니다.',
                    max: '6등급 점수는 100.0000 이하여야 합니다.'
                },
                grade6RecordMin: {
                    required: '6등급 기록 최소값을 입력하세요.',
                    number: '6등급 기록 최소값은 숫자로 입력하세요.',
                    min: '6등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '6등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade6RecordMax: {
                    required: '6등급 기록 최대값을 입력하세요.',
                    number: '6등급 기록 최대값은 숫자로 입력하세요.',
                    min: '6등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '6등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade7Score: {
                    required: '7등급 점수를 입력하세요.',
                    number: '7등급 점수는 숫자로 입력하세요.',
                    min: '7등급 점수는 0 이상이어야 합니다.',
                    max: '7등급 점수는 100.0000 이하여야 합니다.'
                },
                grade7RecordMin: {
                    required: '7등급 기록 최소값을 입력하세요.',
                    number: '7등급 기록 최소값은 숫자로 입력하세요.',
                    min: '7등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '7등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade7RecordMax: {
                    required: '7등급 기록 최대값을 입력하세요.',
                    number: '7등급 기록 최대값은 숫자로 입력하세요.',
                    min: '7등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '7등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade8Score: {
                    required: '8등급 점수를 입력하세요.',
                    number: '8등급 점수는 숫자로 입력하세요.',
                    min: '8등급 점수는 0 이상이어야 합니다.',
                    max: '8등급 점수는 100.0000 이하여야 합니다.'
                },
                grade8RecordMin: {
                    required: '8등급 기록 최소값을 입력하세요.',
                    number: '8등급 기록 최소값은 숫자로 입력하세요.',
                    min: '8등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '8등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade8RecordMax: {
                    required: '8등급 기록 최대값을 입력하세요.',
                    number: '8등급 기록 최대값은 숫자로 입력하세요.',
                    min: '8등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '8등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade9Score: {
                    required: '9등급 점수를 입력하세요.',
                    number: '9등급 점수는 숫자로 입력하세요.',
                    min: '9등급 점수는 0 이상이어야 합니다.',
                    max: '9등급 점수는 100.0000 이하여야 합니다.'
                },
                grade9RecordMin: {
                    required: '9등급 기록 최소값을 입력하세요.',
                    number: '9등급 기록 최소값은 숫자로 입력하세요.',
                    min: '9등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '9등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade9RecordMax: {
                    required: '9등급 기록 최대값을 입력하세요.',
                    number: '9등급 기록 최대값은 숫자로 입력하세요.',
                    min: '9등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '9등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade10Score: {
                    required: '10등급 점수를 입력하세요.',
                    number: '10등급 점수는 숫자로 입력하세요.',
                    min: '10등급 점수는 0 이상이어야 합니다.',
                    max: '10등급 점수는 100.0000 이하여야 합니다.'
                },
                grade10RecordMin: {
                    required: '10등급 기록 최소값을 입력하세요.',
                    number: '10등급 기록 최소값은 숫자로 입력하세요.',
                    min: '10등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '10등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade10RecordMax: {
                    required: '10등급 기록 최대값을 입력하세요.',
                    number: '10등급 기록 최대값은 숫자로 입력하세요.',
                    min: '10등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '10등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade11Score: {
                    required: '11등급 점수를 입력하세요.',
                    number: '11등급 점수는 숫자로 입력하세요.',
                    min: '11등급 점수는 0 이상이어야 합니다.',
                    max: '11등급 점수는 100.0000 이하여야 합니다.'
                },
                grade11RecordMin: {
                    required: '11등급 기록 최소값을 입력하세요.',
                    number: '11등급 기록 최소값은 숫자로 입력하세요.',
                    min: '11등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '11등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade11RecordMax: {
                    required: '11등급 기록 최대값을 입력하세요.',
                    number: '11등급 기록 최대값은 숫자로 입력하세요.',
                    min: '11등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '11등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade12Score: {
                    required: '12등급 점수를 입력하세요.',
                    number: '12등급 점수는 숫자로 입력하세요.',
                    min: '12등급 점수는 0 이상이어야 합니다.',
                    max: '12등급 점수는 100.0000 이하여야 합니다.'
                },
                grade12RecordMin: {
                    required: '12등급 기록 최소값을 입력하세요.',
                    number: '12등급 기록 최소값은 숫자로 입력하세요.',
                    min: '12등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '12등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade12RecordMax: {
                    required: '12등급 기록 최대값을 입력하세요.',
                    number: '12등급 기록 최대값은 숫자로 입력하세요.',
                    min: '12등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '12등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade13Score: {
                    required: '13등급 점수를 입력하세요.',
                    number: '13등급 점수는 숫자로 입력하세요.',
                    min: '13등급 점수는 0 이상이어야 합니다.',
                    max: '13등급 점수는 100.0000 이하여야 합니다.'
                },
                grade13RecordMin: {
                    required: '13등급 기록 최소값을 입력하세요.',
                    number: '13등급 기록 최소값은 숫자로 입력하세요.',
                    min: '13등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '13등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade13RecordMax: {
                    required: '13등급 기록 최대값을 입력하세요.',
                    number: '13등급 기록 최대값은 숫자로 입력하세요.',
                    min: '13등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '13등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade14Score: {
                    required: '14등급 점수를 입력하세요.',
                    number: '14등급 점수는 숫자로 입력하세요.',
                    min: '14등급 점수는 0 이상이어야 합니다.',
                    max: '14등급 점수는 100.0000 이하여야 합니다.'
                },
                grade14RecordMin: {
                    required: '14등급 기록 최소값을 입력하세요.',
                    number: '14등급 기록 최소값은 숫자로 입력하세요.',
                    min: '14등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '14등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade14RecordMax: {
                    required: '14등급 기록 최대값을 입력하세요.',
                    number: '14등급 기록 최대값은 숫자로 입력하세요.',
                    min: '14등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '14등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade15Score: {
                    required: '15등급 점수를 입력하세요.',
                    number: '15등급 점수는 숫자로 입력하세요.',
                    min: '15등급 점수는 0 이상이어야 합니다.',
                    max: '15등급 점수는 100.0000 이하여야 합니다.'
                },
                grade15RecordMin: {
                    required: '15등급 기록 최소값을 입력하세요.',
                    number: '15등급 기록 최소값은 숫자로 입력하세요.',
                    min: '15등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '15등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade15RecordMax: {
                    required: '15등급 기록 최대값을 입력하세요.',
                    number: '15등급 기록 최대값은 숫자로 입력하세요.',
                    min: '15등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '15등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade16Score: {
                    required: '16등급 점수를 입력하세요.',
                    number: '16등급 점수는 숫자로 입력하세요.',
                    min: '16등급 점수는 0 이상이어야 합니다.',
                    max: '16등급 점수는 100.0000 이하여야 합니다.'
                },
                grade16RecordMin: {
                    required: '16등급 기록 최소값을 입력하세요.',
                    number: '16등급 기록 최소값은 숫자로 입력하세요.',
                    min: '16등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '16등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade16RecordMax: {
                    required: '16등급 기록 최대값을 입력하세요.',
                    number: '16등급 기록 최대값은 숫자로 입력하세요.',
                    min: '16등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '16등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade17Score: {
                    required: '17등급 점수를 입력하세요.',
                    number: '17등급 점수는 숫자로 입력하세요.',
                    min: '17등급 점수는 0 이상이어야 합니다.',
                    max: '17등급 점수는 100.0000 이하여야 합니다.'
                },
                grade17RecordMin: {
                    required: '17등급 기록 최소값을 입력하세요.',
                    number: '17등급 기록 최소값은 숫자로 입력하세요.',
                    min: '17등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '17등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade17RecordMax: {
                    required: '17등급 기록 최대값을 입력하세요.',
                    number: '17등급 기록 최대값은 숫자로 입력하세요.',
                    min: '17등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '17등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade18Score: {
                    required: '18등급 점수를 입력하세요.',
                    number: '18등급 점수는 숫자로 입력하세요.',
                    min: '18등급 점수는 0 이상이어야 합니다.',
                    max: '18등급 점수는 100.0000 이하여야 합니다.'
                },
                grade18RecordMin: {
                    required: '18등급 기록 최소값을 입력하세요.',
                    number: '18등급 기록 최소값은 숫자로 입력하세요.',
                    min: '18등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '18등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade18RecordMax: {
                    required: '18등급 기록 최대값을 입력하세요.',
                    number: '18등급 기록 최대값은 숫자로 입력하세요.',
                    min: '18등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '18등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade19Score: {
                    required: '19등급 점수를 입력하세요.',
                    number: '19등급 점수는 숫자로 입력하세요.',
                    min: '19등급 점수는 0 이상이어야 합니다.',
                    max: '19등급 점수는 100.0000 이하여야 합니다.'
                },
                grade19RecordMin: {
                    required: '19등급 기록 최소값을 입력하세요.',
                    number: '19등급 기록 최소값은 숫자로 입력하세요.',
                    min: '19등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '19등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade19RecordMax: {
                    required: '19등급 기록 최대값을 입력하세요.',
                    number: '19등급 기록 최대값은 숫자로 입력하세요.',
                    min: '19등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '19등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade20Score: {
                    required: '20등급 점수를 입력하세요.',
                    number: '20등급 점수는 숫자로 입력하세요.',
                    min: '20등급 점수는 0 이상이어야 합니다.',
                    max: '20등급 점수는 100.0000 이하여야 합니다.'
                },
                grade20RecordMin: {
                    required: '20등급 기록 최소값을 입력하세요.',
                    number: '20등급 기록 최소값은 숫자로 입력하세요.',
                    min: '20등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '20등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade20RecordMax: {
                    required: '20등급 기록 최대값을 입력하세요.',
                    number: '20등급 기록 최대값은 숫자로 입력하세요.',
                    min: '20등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '20등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade21Score: {
                    required: '21등급 점수를 입력하세요.',
                    number: '21등급 점수는 숫자로 입력하세요.',
                    min: '21등급 점수는 0 이상이어야 합니다.',
                    max: '21등급 점수는 100.0000 이하여야 합니다.'
                },
                grade21RecordMin: {
                    required: '21등급 기록 최소값을 입력하세요.',
                    number: '21등급 기록 최소값은 숫자로 입력하세요.',
                    min: '21등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '21등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade21RecordMax: {
                    required: '21등급 기록 최대값을 입력하세요.',
                    number: '21등급 기록 최대값은 숫자로 입력하세요.',
                    min: '21등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '21등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade22Score: {
                    required: '22등급 점수를 입력하세요.',
                    number: '22등급 점수는 숫자로 입력하세요.',
                    min: '22등급 점수는 0 이상이어야 합니다.',
                    max: '22등급 점수는 100.0000 이하여야 합니다.'
                },
                grade22RecordMin: {
                    required: '22등급 기록 최소값을 입력하세요.',
                    number: '22등급 기록 최소값은 숫자로 입력하세요.',
                    min: '22등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '22등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade22RecordMax: {
                    required: '22등급 기록 최대값을 입력하세요.',
                    number: '22등급 기록 최대값은 숫자로 입력하세요.',
                    min: '22등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '22등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade23Score: {
                    required: '23등급 점수를 입력하세요.',
                    number: '23등급 점수는 숫자로 입력하세요.',
                    min: '23등급 점수는 0 이상이어야 합니다.',
                    max: '23등급 점수는 100.0000 이하여야 합니다.'
                },
                grade23RecordMin: {
                    required: '23등급 기록 최소값을 입력하세요.',
                    number: '23등급 기록 최소값은 숫자로 입력하세요.',
                    min: '23등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '23등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade23RecordMax: {
                    required: '23등급 기록 최대값을 입력하세요.',
                    number: '23등급 기록 최대값은 숫자로 입력하세요.',
                    min: '23등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '23등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade24Score: {
                    required: '24등급 점수를 입력하세요.',
                    number: '24등급 점수는 숫자로 입력하세요.',
                    min: '24등급 점수는 0 이상이어야 합니다.',
                    max: '24등급 점수는 100.0000 이하여야 합니다.'
                },
                grade24RecordMin: {
                    required: '24등급 기록 최소값을 입력하세요.',
                    number: '24등급 기록 최소값은 숫자로 입력하세요.',
                    min: '24등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '24등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade24RecordMax: {
                    required: '24등급 기록 최대값을 입력하세요.',
                    number: '24등급 기록 최대값은 숫자로 입력하세요.',
                    min: '24등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '24등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade25Score: {
                    required: '25등급 점수를 입력하세요.',
                    number: '25등급 점수는 숫자로 입력하세요.',
                    min: '25등급 점수는 0 이상이어야 합니다.',
                    max: '25등급 점수는 100.0000 이하여야 합니다.'
                },
                grade25RecordMin: {
                    required: '25등급 기록 최소값을 입력하세요.',
                    number: '25등급 기록 최소값은 숫자로 입력하세요.',
                    min: '25등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '25등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade25RecordMax: {
                    required: '25등급 기록 최대값을 입력하세요.',
                    number: '25등급 기록 최대값은 숫자로 입력하세요.',
                    min: '25등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '25등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade26Score: {
                    required: '26등급 점수를 입력하세요.',
                    number: '26등급 점수는 숫자로 입력하세요.',
                    min: '26등급 점수는 0 이상이어야 합니다.',
                    max: '26등급 점수는 100.0000 이하여야 합니다.'
                },
                grade26RecordMin: {
                    required: '26등급 기록 최소값을 입력하세요.',
                    number: '26등급 기록 최소값은 숫자로 입력하세요.',
                    min: '26등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '26등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade26RecordMax: {
                    required: '26등급 기록 최대값을 입력하세요.',
                    number: '26등급 기록 최대값은 숫자로 입력하세요.',
                    min: '26등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '26등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade27Score: {
                    required: '27등급 점수를 입력하세요.',
                    number: '27등급 점수는 숫자로 입력하세요.',
                    min: '27등급 점수는 0 이상이어야 합니다.',
                    max: '27등급 점수는 100.0000 이하여야 합니다.'
                },
                grade27RecordMin: {
                    required: '27등급 기록 최소값을 입력하세요.',
                    number: '27등급 기록 최소값은 숫자로 입력하세요.',
                    min: '27등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '27등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade27RecordMax: {
                    required: '27등급 기록 최대값을 입력하세요.',
                    number: '27등급 기록 최대값은 숫자로 입력하세요.',
                    min: '27등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '27등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade28Score: {
                    required: '28등급 점수를 입력하세요.',
                    number: '28등급 점수는 숫자로 입력하세요.',
                    min: '28등급 점수는 0 이상이어야 합니다.',
                    max: '28등급 점수는 100.0000 이하여야 합니다.'
                },
                grade28RecordMin: {
                    required: '28등급 기록 최소값을 입력하세요.',
                    number: '28등급 기록 최소값은 숫자로 입력하세요.',
                    min: '28등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '28등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade28RecordMax: {
                    required: '28등급 기록 최대값을 입력하세요.',
                    number: '28등급 기록 최대값은 숫자로 입력하세요.',
                    min: '28등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '28등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade29Score: {
                    required: '29등급 점수를 입력하세요.',
                    number: '29등급 점수는 숫자로 입력하세요.',
                    min: '29등급 점수는 0 이상이어야 합니다.',
                    max: '29등급 점수는 100.0000 이하여야 합니다.'
                },
                grade29RecordMin: {
                    required: '29등급 기록 최소값을 입력하세요.',
                    number: '29등급 기록 최소값은 숫자로 입력하세요.',
                    min: '29등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '29등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade29RecordMax: {
                    required: '29등급 기록 최대값을 입력하세요.',
                    number: '29등급 기록 최대값은 숫자로 입력하세요.',
                    min: '29등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '29등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade30Score: {
                    required: '30등급 점수를 입력하세요.',
                    number: '30등급 점수는 숫자로 입력하세요.',
                    min: '30등급 점수는 0 이상이어야 합니다.',
                    max: '30등급 점수는 100.0000 이하여야 합니다.'
                },
                grade30RecordMin: {
                    required: '30등급 기록 최소값을 입력하세요.',
                    number: '30등급 기록 최소값은 숫자로 입력하세요.',
                    min: '30등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '30등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade30RecordMax: {
                    required: '30등급 기록 최대값을 입력하세요.',
                    number: '30등급 기록 최대값은 숫자로 입력하세요.',
                    min: '30등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '30등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade31Score: {
                    required: '31등급 점수를 입력하세요.',
                    number: '31등급 점수는 숫자로 입력하세요.',
                    min: '31등급 점수는 0 이상이어야 합니다.',
                    max: '31등급 점수는 100.0000 이하여야 합니다.'
                },
                grade31RecordMin: {
                    required: '31등급 기록 최소값을 입력하세요.',
                    number: '31등급 기록 최소값은 숫자로 입력하세요.',
                    min: '31등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '31등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade31RecordMax: {
                    required: '31등급 기록 최대값을 입력하세요.',
                    number: '31등급 기록 최대값은 숫자로 입력하세요.',
                    min: '31등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '31등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade32Score: {
                    required: '32등급 점수를 입력하세요.',
                    number: '32등급 점수는 숫자로 입력하세요.',
                    min: '32등급 점수는 0 이상이어야 합니다.',
                    max: '32등급 점수는 100.0000 이하여야 합니다.'
                },
                grade32RecordMin: {
                    required: '32등급 기록 최소값을 입력하세요.',
                    number: '32등급 기록 최소값은 숫자로 입력하세요.',
                    min: '32등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '32등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade32RecordMax: {
                    required: '32등급 기록 최대값을 입력하세요.',
                    number: '32등급 기록 최대값은 숫자로 입력하세요.',
                    min: '32등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '32등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade33Score: {
                    required: '33등급 점수를 입력하세요.',
                    number: '33등급 점수는 숫자로 입력하세요.',
                    min: '33등급 점수는 0 이상이어야 합니다.',
                    max: '33등급 점수는 100.0000 이하여야 합니다.'
                },
                grade33RecordMin: {
                    required: '33등급 기록 최소값을 입력하세요.',
                    number: '33등급 기록 최소값은 숫자로 입력하세요.',
                    min: '33등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '33등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade33RecordMax: {
                    required: '33등급 기록 최대값을 입력하세요.',
                    number: '33등급 기록 최대값은 숫자로 입력하세요.',
                    min: '33등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '33등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade34Score: {
                    required: '34등급 점수를 입력하세요.',
                    number: '34등급 점수는 숫자로 입력하세요.',
                    min: '34등급 점수는 0 이상이어야 합니다.',
                    max: '34등급 점수는 100.0000 이하여야 합니다.'
                },
                grade34RecordMin: {
                    required: '34등급 기록 최소값을 입력하세요.',
                    number: '34등급 기록 최소값은 숫자로 입력하세요.',
                    min: '34등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '34등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade34RecordMax: {
                    required: '34등급 기록 최대값을 입력하세요.',
                    number: '34등급 기록 최대값은 숫자로 입력하세요.',
                    min: '34등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '34등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade35Score: {
                    required: '35등급 점수를 입력하세요.',
                    number: '35등급 점수는 숫자로 입력하세요.',
                    min: '35등급 점수는 0 이상이어야 합니다.',
                    max: '35등급 점수는 100.0000 이하여야 합니다.'
                },
                grade35RecordMin: {
                    required: '35등급 기록 최소값을 입력하세요.',
                    number: '35등급 기록 최소값은 숫자로 입력하세요.',
                    min: '35등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '35등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade35RecordMax: {
                    required: '35등급 기록 최대값을 입력하세요.',
                    number: '35등급 기록 최대값은 숫자로 입력하세요.',
                    min: '35등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '35등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade36Score: {
                    required: '36등급 점수를 입력하세요.',
                    number: '36등급 점수는 숫자로 입력하세요.',
                    min: '36등급 점수는 0 이상이어야 합니다.',
                    max: '36등급 점수는 100.0000 이하여야 합니다.'
                },
                grade36RecordMin: {
                    required: '36등급 기록 최소값을 입력하세요.',
                    number: '36등급 기록 최소값은 숫자로 입력하세요.',
                    min: '36등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '36등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade36RecordMax: {
                    required: '36등급 기록 최대값을 입력하세요.',
                    number: '36등급 기록 최대값은 숫자로 입력하세요.',
                    min: '36등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '36등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade37Score: {
                    required: '37등급 점수를 입력하세요.',
                    number: '37등급 점수는 숫자로 입력하세요.',
                    min: '37등급 점수는 0 이상이어야 합니다.',
                    max: '37등급 점수는 100.0000 이하여야 합니다.'
                },
                grade37RecordMin: {
                    required: '37등급 기록 최소값을 입력하세요.',
                    number: '37등급 기록 최소값은 숫자로 입력하세요.',
                    min: '37등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '37등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade37RecordMax: {
                    required: '37등급 기록 최대값을 입력하세요.',
                    number: '37등급 기록 최대값은 숫자로 입력하세요.',
                    min: '37등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '37등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade38Score: {
                    required: '38등급 점수를 입력하세요.',
                    number: '38등급 점수는 숫자로 입력하세요.',
                    min: '38등급 점수는 0 이상이어야 합니다.',
                    max: '38등급 점수는 100.0000 이하여야 합니다.'
                },
                grade38RecordMin: {
                    required: '38등급 기록 최소값을 입력하세요.',
                    number: '38등급 기록 최소값은 숫자로 입력하세요.',
                    min: '38등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '38등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade38RecordMax: {
                    required: '38등급 기록 최대값을 입력하세요.',
                    number: '38등급 기록 최대값은 숫자로 입력하세요.',
                    min: '38등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '38등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade39Score: {
                    required: '39등급 점수를 입력하세요.',
                    number: '39등급 점수는 숫자로 입력하세요.',
                    min: '39등급 점수는 0 이상이어야 합니다.',
                    max: '39등급 점수는 100.0000 이하여야 합니다.'
                },
                grade39RecordMin: {
                    required: '39등급 기록 최소값을 입력하세요.',
                    number: '39등급 기록 최소값은 숫자로 입력하세요.',
                    min: '39등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '39등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade39RecordMax: {
                    required: '39등급 기록 최대값을 입력하세요.',
                    number: '39등급 기록 최대값은 숫자로 입력하세요.',
                    min: '39등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '39등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                grade40Score: {
                    required: '40등급 점수를 입력하세요.',
                    number: '40등급 점수는 숫자로 입력하세요.',
                    min: '40등급 점수는 0 이상이어야 합니다.',
                    max: '40등급 점수는 100.0000 이하여야 합니다.'
                },
                grade40RecordMin: {
                    required: '40등급 기록 최소값을 입력하세요.',
                    number: '40등급 기록 최소값은 숫자로 입력하세요.',
                    min: '40등급 기록 최소값은 0 이상이어야 합니다.',
                    max: '40등급 기록 최소값은 100.0000 이하여야 합니다.'
                },
                grade40RecordMax: {
                    required: '40등급 기록 최대값을 입력하세요.',
                    number: '40등급 기록 최대값은 숫자로 입력하세요.',
                    min: '40등급 기록 최대값은 0 이상이어야 합니다.',
                    max: '40등급 기록 최대값은 1000.0000 이하여야 합니다.'
                },
                earlyAdmissionPhysicalAbsoluteMemo: {
                    maxlength: '메모는 최대 500자 이하여야 합니다.'
                },
            },
            errorClass: 'is-invalid',
            validClass: 'is-valid',
            errorPlacement: (error, element) => {
                error.addClass('invalid-feedback');
                element.closest('.mb-3').append(error);
            }
        });

        // 저장 버튼 이벤트 핸들러
        $(SAVE_BUTTON_ID).click(function(e) {
            e.preventDefault();
            
            if (!$form.valid()) return;

            const data = getFormData($form);
            const url = '/admin/earlyAdmissionPhysical' + (gender == 'man' ? 'Man' : 'Woman') + 'Absolute/' + action;
            
            $.ajax({
                url,
                type: 'POST',
                data,
                success: (response) => {
                    if (response.success) {
                        alert('수시 입시 실기 ' + (gender == 'man' ? '남자' : '여자') + ' 절대평가 점수가 저장되었습니다.');
                        $(MODAL_ID).modal('hide');
                        updateButtonState(id, 'update', gender);
                    } else {
                        alert('수시 입시 실기 ' + (gender == 'man' ? '남자' : '여자') + ' 절대평가 점수 저장에 실패했습니다.');
                    }
                },
                error: (xhr, status, error) => {
                    alert('저장 중 오류가 발생했습니다. 다시 시도해주세요.');
                    console.error('Error:', error);
                }
            });
        });

        // 등급 사용 여부 변경 이벤트
        for (let i = 2; i <= 40; i++) {
            $('#useGrade' + i).change(function() {
                const useGrade = $(this).val();
                const currentGrade = i;
                
                if (useGrade == 'Y') {
                    // 현재 등급의 입력 필드 활성화
                    $('#grade' + currentGrade + 'Score, #grade' + currentGrade + 'RecordMin, #grade' + currentGrade + 'RecordMax')
                        .prop('disabled', false);
                    
                    // 다음 등급의 사용 여부 선택 활성화
                    if (currentGrade < 40) {
                        $('#useGrade' + (currentGrade + 1)).prop('disabled', false);
                    }
                } else {
                    // 현재 등급의 입력 필드 비활성화
                    $('#grade' + currentGrade + 'Score, #grade' + currentGrade + 'RecordMin, #grade' + currentGrade + 'RecordMax')
                        .prop('disabled', true);
                    
                    // 현재 등급 이후의 모든 등급 비활성화
                    for (let j = currentGrade + 1; j <= 40; j++) {
                        $('#useGrade' + j).val('N').prop('disabled', true);
                        $('#grade' + j + 'Score, #grade' + j + 'RecordMin, #grade' + j + 'RecordMax')
                            .prop('disabled', true);
                    }
                }
            });
        }
    });
</script>