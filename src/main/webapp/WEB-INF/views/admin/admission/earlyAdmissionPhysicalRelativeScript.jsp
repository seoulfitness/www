<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script>
    $(document).ready(function() {
        // 유틸리티 함수
        const getRelativeFormData = ($relativeForm) => {
            const formData = {};
            
            // form 데이터를 객체로 변환
            const formArray = $relativeForm.serializeArray();
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
                additionalData['earlyAdmissionPhysicalRelativeId'] = earlyAdmissionPhysicalRelativeId;
            }

            return {
                ...formData,
                ...additionalData
            };
        };       

        const updateRelativeButtonState = (id, action, gender) => {
            $('.btn-' + gender + '-relative-score[data-id="' + id + '"]')
                .removeClass('btn-outline-danger')
                .addClass('btn-primary')
                .text('배점 보기');
        };

        // 상대평가 점수 버튼 클릭 이벤트
        $('.btn-relative-score').click(function(e) {
            e.preventDefault();
            
            // 데이터 초기화
            btnId = $(this).data('id');
            admissionId = $(this).data('admission-id');
            earlyAdmissionPhysicalId = $(this).data('early-admission-physical-id');
            earlyAdmissionPhysicalSubjectId = $(this).data('subject-id');
            earlyAdmissionPhysicalRelativeId = $(this).data('early-admission-physical-relative-id');
            subjectName = $(this).data('subject-name');
            gender = $(this).data('gender');
            action = $(this).data('action');

            // action이 create일 경우 모든 등급의 점수와 범위을 0.0000으로 초기화
            if (action == 'create') {
                // 1등급 초기화: 사용함, 입력 가능
                $('#useGrade1').val('Y');
                $('#grade1Score, #grade1RangeMin, #grade1RangeMax')
                    .val('0.0000')
                    .prop('disabled', false);

                // 2-40등급 초기화: 사용안함, 입력 불가
                for (let i = 2; i <= 40; i++) {
                    $('#useGrade' + i).val('N').prop('disabled', true);
                    $('#grade' + i + 'Score, #grade' + i + 'RangeMin, #grade' + i + 'RangeMax')
                        .val('0.0000')
                        .prop('disabled', true);
                }

                // 2등급 사용 여부 설정
                $('#useGrade2').val('N').prop('disabled', false);
            } else {
                let url = '/admin/earlyAdmissionPhysical' + (gender == 'man' ? 'Man' : 'Woman') + 'Relative/' + earlyAdmissionPhysicalRelativeId;
                // 점수 불러오기
                $.ajax({
                    url: url,
                    type: 'GET',
                    success: (response) => {
                        const data = response.earlyAdmissionPhysicalRelative;

                        // 1등급 ~ 40등급 사용 여부 설정
                        for (let i = 1; i <= 40; i++) {
                            $('#useGrade' + i).val(data['useGrade' + i] || 'N').prop('disabled', !data['useGrade' + i]);
                        }

                        // 1등급 ~ 40등급 점수 불러오기
                        for (let i = 1; i <= 40; i++) {
                            $('#grade' + i + 'Score').val(data['grade' + i + 'Score'] || '0.0000');
                            $('#grade' + i + 'RangeMin').val(data['grade' + i + 'RangeMin'] || '0.0000');
                            $('#grade' + i + 'RangeMax').val(data['grade' + i + 'RangeMax'] || '0.0000');
                        }

                        // 1등급부터 40등급까지 사용여부 확인한 후 사용 여부 설정
                        for (let i = 1; i <= 40; i++) {
                            if (data['useGrade' + i] == 'Y') {
                                $('#grade' + i + 'Score').val(data['grade' + i + 'Score'] || '0.0000').prop('disabled', false);
                                $('#grade' + i + 'RangeMin').val(data['grade' + i + 'RangeMin'] || '0.0000').prop('disabled', false);
                                $('#grade' + i + 'RangeMax').val(data['grade' + i + 'RangeMax'] || '0.0000').prop('disabled', false);
                                $('#useGrade' + (i + 1)).prop('disabled', false);
                            } else {
                                $('#grade' + i + 'Score').val('0.0000').prop('disabled', true);
                                $('#grade' + i + 'RangeMin').val('0.0000').prop('disabled', true);
                                $('#grade' + i + 'RangeMax').val('0.0000').prop('disabled', true);
                                $('#useGrade' + (i + 1)).prop('disabled', true);
                            }
                        }

                        // 메모 불러오기
                        $('#earlyAdmissionPhysicalRelativeMemo').val(data.earlyAdmissionPhysicalRelativeMemo || '');
                    },
                    error: (xhr, status, error) => {
                        alert('점수 정보를 불러오는 중 오류가 발생했습니다. 다시 시도해주세요.');
                        console.error('Error:', error);
                    }
                });
            }

            // 모달 제목 설정
            const genderText = gender == 'man' ? '남자' : '여자';
            const modalTitle = subjectName + ' ' + genderText + ' 상대평가 점수';
            $('.early-admission-physical-relative-score-modal-title').text(modalTitle);

            // 모달 표시
            $(RELATIVE_MODAL_ID).modal('show');
        });

        // 폼 유효성 검사 설정
        const $relativeForm = $(RELATIVE_FORM_ID);
        const validator = $relativeForm.validate({
            rules: {
                grade1Score: {
                    required: true,
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade1RangeMin: {
                    required: true,
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade1RangeMax: {
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
                grade2RangeMin: {
                    required: function() {
                        return $('#useGrade2').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade2RangeMax: {
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
                grade3RangeMin: {
                    required: function() {
                        return $('#useGrade3').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade3RangeMax: {
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
                grade4RangeMin: {
                    required: function() {
                        return $('#useGrade4').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade4RangeMax: {
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
                grade5RangeMin: {
                    required: function() {
                        return $('#useGrade5').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade5RangeMax: {
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
                grade6RangeMin: {
                    required: function() {
                        return $('#useGrade6').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade6RangeMax: {
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
                grade7RangeMin: {
                    required: function() {
                        return $('#useGrade7').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade7RangeMax: {
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
                grade8RangeMin: {
                    required: function() {
                        return $('#useGrade8').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade8RangeMax: {
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
                grade9RangeMin: {
                    required: function() {
                        return $('#useGrade9').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade9RangeMax: {
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
                grade10RangeMin: {
                    required: function() {
                        return $('#useGrade10').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade10RangeMax: {
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
                grade11RangeMin: {
                    required: function() {
                        return $('#useGrade11').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade11RangeMax: {
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
                grade12RangeMin: {
                    required: function() {
                        return $('#useGrade12').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade12RangeMax: {
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
                grade13RangeMin: {
                    required: function() {
                        return $('#useGrade13').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade13RangeMax: {
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
                grade14RangeMin: {
                    required: function() {
                        return $('#useGrade14').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade14RangeMax: {
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
                grade15RangeMin: {
                    required: function() {
                        return $('#useGrade15').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade15RangeMax: {
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
                grade16RangeMin: {
                    required: function() {
                        return $('#useGrade16').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade16RangeMax: {
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
                grade17RangeMin: {
                    required: function() {
                        return $('#useGrade17').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade17RangeMax: {
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
                grade18RangeMin: {
                    required: function() {
                        return $('#useGrade18').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade18RangeMax: {
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
                grade19RangeMin: {
                    required: function() {
                        return $('#useGrade19').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade19RangeMax: {
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
                grade20RangeMin: {
                    required: '20등급 범위 최소값을 입력하세요.',
                    number: '20등급 범위 최소값은 숫자로 입력하세요.',
                    min: '20등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '20등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade20RangeMax: {
                    required: '20등급 범위 최대값을 입력하세요.',
                    number: '20등급 범위 최대값은 숫자로 입력하세요.',
                    min: '20등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '20등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade21Score: {
                    required: '21등급 점수를 입력하세요.',
                    number: '21등급 점수는 숫자로 입력하세요.',
                    min: '21등급 점수는 0 이상이어야 합니다.',
                    max: '21등급 점수는 100.0000 이하여야 합니다.'
                },
                grade21RangeMin: {
                    required: '21등급 범위 최소값을 입력하세요.',
                    number: '21등급 범위 최소값은 숫자로 입력하세요.',
                    min: '21등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '21등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade21RangeMax: {
                    required: '21등급 범위 최대값을 입력하세요.',
                    number: '21등급 범위 최대값은 숫자로 입력하세요.',
                    min: '21등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '21등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade22Score: {
                    required: '22등급 점수를 입력하세요.',
                    number: '22등급 점수는 숫자로 입력하세요.',
                    min: '22등급 점수는 0 이상이어야 합니다.',
                    max: '22등급 점수는 100.0000 이하여야 합니다.'
                },
                grade22RangeMin: {
                    required: '22등급 범위 최소값을 입력하세요.',
                    number: '22등급 범위 최소값은 숫자로 입력하세요.',
                    min: '22등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '22등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade22RangeMax: {
                    required: '22등급 범위 최대값을 입력하세요.',
                    number: '22등급 범위 최대값은 숫자로 입력하세요.',
                    min: '22등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '22등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade23Score: {
                    required: '23등급 점수를 입력하세요.',
                    number: '23등급 점수는 숫자로 입력하세요.',
                    min: '23등급 점수는 0 이상이어야 합니다.',
                    max: '23등급 점수는 100.0000 이하여야 합니다.'
                },
                grade23RangeMin: {
                    required: '23등급 범위 최소값을 입력하세요.',
                    number: '23등급 범위 최소값은 숫자로 입력하세요.',
                    min: '23등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '23등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade23RangeMax: {
                    required: '23등급 범위 최대값을 입력하세요.',
                    number: '23등급 범위 최대값은 숫자로 입력하세요.',
                    min: '23등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '23등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade24Score: {
                    required: '24등급 점수를 입력하세요.',
                    number: '24등급 점수는 숫자로 입력하세요.',
                    min: '24등급 점수는 0 이상이어야 합니다.',
                    max: '24등급 점수는 100.0000 이하여야 합니다.'
                },
                grade24RangeMin: {
                    required: '24등급 범위 최소값을 입력하세요.',
                    number: '24등급 범위 최소값은 숫자로 입력하세요.',
                    min: '24등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '24등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade24RangeMax: {
                    required: '24등급 범위 최대값을 입력하세요.',
                    number: '24등급 범위 최대값은 숫자로 입력하세요.',
                    min: '24등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '24등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade25Score: {
                    required: '25등급 점수를 입력하세요.',
                    number: '25등급 점수는 숫자로 입력하세요.',
                    min: '25등급 점수는 0 이상이어야 합니다.',
                    max: '25등급 점수는 100.0000 이하여야 합니다.'
                },
                grade25RangeMin: {
                    required: '25등급 범위 최소값을 입력하세요.',
                    number: '25등급 범위 최소값은 숫자로 입력하세요.',
                    min: '25등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '25등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade25RangeMax: {
                    required: '25등급 범위 최대값을 입력하세요.',
                    number: '25등급 범위 최대값은 숫자로 입력하세요.',
                    min: '25등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '25등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade26Score: {
                    required: '26등급 점수를 입력하세요.',
                    number: '26등급 점수는 숫자로 입력하세요.',
                    min: '26등급 점수는 0 이상이어야 합니다.',
                    max: '26등급 점수는 100.0000 이하여야 합니다.'
                },
                grade26RangeMin: {
                    required: '26등급 범위 최소값을 입력하세요.',
                    number: '26등급 범위 최소값은 숫자로 입력하세요.',
                    min: '26등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '26등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade26RangeMax: {
                    required: '26등급 범위 최대값을 입력하세요.',
                    number: '26등급 범위 최대값은 숫자로 입력하세요.',
                    min: '26등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '26등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade27Score: {
                    required: '27등급 점수를 입력하세요.',
                    number: '27등급 점수는 숫자로 입력하세요.',
                    min: '27등급 점수는 0 이상이어야 합니다.',
                    max: '27등급 점수는 100.0000 이하여야 합니다.'
                },
                grade27RangeMin: {
                    required: '27등급 범위 최소값을 입력하세요.',
                    number: '27등급 범위 최소값은 숫자로 입력하세요.',
                    min: '27등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '27등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade27RangeMax: {
                    required: '27등급 범위 최대값을 입력하세요.',
                    number: '27등급 범위 최대값은 숫자로 입력하세요.',
                    min: '27등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '27등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade28Score: {
                    required: '28등급 점수를 입력하세요.',
                    number: '28등급 점수는 숫자로 입력하세요.',
                    min: '28등급 점수는 0 이상이어야 합니다.',
                    max: '28등급 점수는 100.0000 이하여야 합니다.'
                },
                grade28RangeMin: {
                    required: '28등급 범위 최소값을 입력하세요.',
                    number: '28등급 범위 최소값은 숫자로 입력하세요.',
                    min: '28등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '28등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade28RangeMax: {
                    required: '28등급 범위 최대값을 입력하세요.',
                    number: '28등급 범위 최대값은 숫자로 입력하세요.',
                    min: '28등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '28등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade29Score: {
                    required: '29등급 점수를 입력하세요.',
                    number: '29등급 점수는 숫자로 입력하세요.',
                    min: '29등급 점수는 0 이상이어야 합니다.',
                    max: '29등급 점수는 100.0000 이하여야 합니다.'
                },
                grade29RangeMin: {
                    required: '29등급 범위 최소값을 입력하세요.',
                    number: '29등급 범위 최소값은 숫자로 입력하세요.',
                    min: '29등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '29등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade29RangeMax: {
                    required: '29등급 범위 최대값을 입력하세요.',
                    number: '29등급 범위 최대값은 숫자로 입력하세요.',
                    min: '29등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '29등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade30Score: {
                    required: '30등급 점수를 입력하세요.',
                    number: '30등급 점수는 숫자로 입력하세요.',
                    min: '30등급 점수는 0 이상이어야 합니다.',
                    max: '30등급 점수는 100.0000 이하여야 합니다.'
                },
                grade30RangeMin: {
                    required: '30등급 범위 최소값을 입력하세요.',
                    number: '30등급 범위 최소값은 숫자로 입력하세요.',
                    min: '30등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '30등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade30RangeMax: {
                    required: '30등급 범위 최대값을 입력하세요.',
                    number: '30등급 범위 최대값은 숫자로 입력하세요.',
                    min: '30등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '30등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade31Score: {
                    required: '31등급 점수를 입력하세요.',
                    number: '31등급 점수는 숫자로 입력하세요.',
                    min: '31등급 점수는 0 이상이어야 합니다.',
                    max: '31등급 점수는 100.0000 이하여야 합니다.'
                },
                grade31RangeMin: {
                    required: '31등급 범위 최소값을 입력하세요.',
                    number: '31등급 범위 최소값은 숫자로 입력하세요.',
                    min: '31등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '31등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade31RangeMax: {
                    required: '31등급 범위 최대값을 입력하세요.',
                    number: '31등급 범위 최대값은 숫자로 입력하세요.',
                    min: '31등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '31등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade32Score: {
                    required: '32등급 점수를 입력하세요.',
                    number: '32등급 점수는 숫자로 입력하세요.',
                    min: '32등급 점수는 0 이상이어야 합니다.',
                    max: '32등급 점수는 100.0000 이하여야 합니다.'
                },
                grade32RangeMin: {
                    required: '32등급 범위 최소값을 입력하세요.',
                    number: '32등급 범위 최소값은 숫자로 입력하세요.',
                    min: '32등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '32등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade32RangeMax: {
                    required: '32등급 범위 최대값을 입력하세요.',
                    number: '32등급 범위 최대값은 숫자로 입력하세요.',
                    min: '32등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '32등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade33Score: {
                    required: '33등급 점수를 입력하세요.',
                    number: '33등급 점수는 숫자로 입력하세요.',
                    min: '33등급 점수는 0 이상이어야 합니다.',
                    max: '33등급 점수는 100.0000 이하여야 합니다.'
                },
                grade33RangeMin: {
                    required: '33등급 범위 최소값을 입력하세요.',
                    number: '33등급 범위 최소값은 숫자로 입력하세요.',
                    min: '33등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '33등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade33RangeMax: {
                    required: '33등급 범위 최대값을 입력하세요.',
                    number: '33등급 범위 최대값은 숫자로 입력하세요.',
                    min: '33등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '33등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade34Score: {
                    required: '34등급 점수를 입력하세요.',
                    number: '34등급 점수는 숫자로 입력하세요.',
                    min: '34등급 점수는 0 이상이어야 합니다.',
                    max: '34등급 점수는 100.0000 이하여야 합니다.'
                },
                grade34RangeMin: {
                    required: '34등급 범위 최소값을 입력하세요.',
                    number: '34등급 범위 최소값은 숫자로 입력하세요.',
                    min: '34등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '34등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade34RangeMax: {
                    required: '34등급 범위 최대값을 입력하세요.',
                    number: '34등급 범위 최대값은 숫자로 입력하세요.',
                    min: '34등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '34등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade35Score: {
                    required: '35등급 점수를 입력하세요.',
                    number: '35등급 점수는 숫자로 입력하세요.',
                    min: '35등급 점수는 0 이상이어야 합니다.',
                    max: '35등급 점수는 100.0000 이하여야 합니다.'
                },
                grade35RangeMin: {
                    required: '35등급 범위 최소값을 입력하세요.',
                    number: '35등급 범위 최소값은 숫자로 입력하세요.',
                    min: '35등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '35등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade35RangeMax: {
                    required: '35등급 범위 최대값을 입력하세요.',
                    number: '35등급 범위 최대값은 숫자로 입력하세요.',
                    min: '35등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '35등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade36Score: {
                    required: '36등급 점수를 입력하세요.',
                    number: '36등급 점수는 숫자로 입력하세요.',
                    min: '36등급 점수는 0 이상이어야 합니다.',
                    max: '36등급 점수는 100.0000 이하여야 합니다.'
                },
                grade36RangeMin: {
                    required: '36등급 범위 최소값을 입력하세요.',
                    number: '36등급 범위 최소값은 숫자로 입력하세요.',
                    min: '36등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '36등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade36RangeMax: {
                    required: '36등급 범위 최대값을 입력하세요.',
                    number: '36등급 범위 최대값은 숫자로 입력하세요.',
                    min: '36등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '36등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade37Score: {
                    required: '37등급 점수를 입력하세요.',
                    number: '37등급 점수는 숫자로 입력하세요.',
                    min: '37등급 점수는 0 이상이어야 합니다.',
                    max: '37등급 점수는 100.0000 이하여야 합니다.'
                },
                grade37RangeMin: {
                    required: '37등급 범위 최소값을 입력하세요.',
                    number: '37등급 범위 최소값은 숫자로 입력하세요.',
                    min: '37등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '37등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade37RangeMax: {
                    required: '37등급 범위 최대값을 입력하세요.',
                    number: '37등급 범위 최대값은 숫자로 입력하세요.',
                    min: '37등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '37등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade38Score: {
                    required: '38등급 점수를 입력하세요.',
                    number: '38등급 점수는 숫자로 입력하세요.',
                    min: '38등급 점수는 0 이상이어야 합니다.',
                    max: '38등급 점수는 100.0000 이하여야 합니다.'
                },
                grade38RangeMin: {
                    required: '38등급 범위 최소값을 입력하세요.',
                    number: '38등급 범위 최소값은 숫자로 입력하세요.',
                    min: '38등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '38등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade38RangeMax: {
                    required: '38등급 범위 최대값을 입력하세요.',
                    number: '38등급 범위 최대값은 숫자로 입력하세요.',
                    min: '38등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '38등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade39Score: {
                    required: '39등급 점수를 입력하세요.',
                    number: '39등급 점수는 숫자로 입력하세요.',
                    min: '39등급 점수는 0 이상이어야 합니다.',
                    max: '39등급 점수는 100.0000 이하여야 합니다.'
                },
                grade39RangeMin: {
                    required: '39등급 범위 최소값을 입력하세요.',
                    number: '39등급 범위 최소값은 숫자로 입력하세요.',
                    min: '39등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '39등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade39RangeMax: {
                    required: '39등급 범위 최대값을 입력하세요.',
                    number: '39등급 범위 최대값은 숫자로 입력하세요.',
                    min: '39등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '39등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade40Score: {
                    required: function() {
                        return $('#useGrade40').val() == 'Y';
                    },
                    number: true,
                    min: 0,
                    max: 100.0000
                },
                grade40RangeMin: {
                    required: '40등급 범위 최소값을 입력하세요.',
                    number: '40등급 범위 최소값은 숫자로 입력하세요.',
                    min: '40등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '40등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade40RangeMax: {
                    required: '40등급 범위 최대값을 입력하세요.',
                    number: '40등급 범위 최대값은 숫자로 입력하세요.',
                    min: '40등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '40등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                earlyAdmissionPhysicalRelativeMemo: {
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
                grade1RangeMin: {
                    required: '1등급 범위 최소값을 입력하세요.',
                    number: '1등급 범위 최소값은 숫자로 입력하세요.',
                    min: '1등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '1등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade1RangeMax: {
                    required: '1등급 범위 최대값을 입력하세요.',
                    number: '1등급 범위 최대값은 숫자로 입력하세요.',
                    min: '1등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '1등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade2Score: {
                    required: '2등급 점수를 입력하세요.',
                    number: '2등급 점수는 숫자로 입력하세요.',
                    min: '2등급 점수는 0 이상이어야 합니다.',
                    max: '2등급 점수는 100.0000 이하여야 합니다.'
                },
                grade2RangeMin: {
                    required: '2등급 범위 최소값을 입력하세요.',
                    number: '2등급 범위 최소값은 숫자로 입력하세요.',
                    min: '2등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '2등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade2RangeMax: {
                    required: '2등급 범위 최대값을 입력하세요.',
                    number: '2등급 범위 최대값은 숫자로 입력하세요.',
                    min: '2등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '2등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade3Score: {
                    required: '3등급 점수를 입력하세요.',
                    number: '3등급 점수는 숫자로 입력하세요.',
                    min: '3등급 점수는 0 이상이어야 합니다.',
                    max: '3등급 점수는 100.0000 이하여야 합니다.'
                },
                grade3RangeMin: {
                    required: '3등급 범위 최소값을 입력하세요.',
                    number: '3등급 범위 최소값은 숫자로 입력하세요.',
                    min: '3등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '3등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade3RangeMax: {
                    required: '3등급 범위 최대값을 입력하세요.',
                    number: '3등급 범위 최대값은 숫자로 입력하세요.',
                    min: '3등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '3등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade4Score: {
                    required: '4등급 점수를 입력하세요.',
                    number: '4등급 점수는 숫자로 입력하세요.',
                    min: '4등급 점수는 0 이상이어야 합니다.',
                    max: '4등급 점수는 100.0000 이하여야 합니다.'
                },
                grade4RangeMin: {
                    required: '4등급 범위 최소값을 입력하세요.',
                    number: '4등급 범위 최소값은 숫자로 입력하세요.',
                    min: '4등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '4등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade4RangeMax: {
                    required: '4등급 범위 최대값을 입력하세요.',
                    number: '4등급 범위 최대값은 숫자로 입력하세요.',
                    min: '4등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '4등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade5Score: {
                    required: '5등급 점수를 입력하세요.',
                    number: '5등급 점수는 숫자로 입력하세요.',
                    min: '5등급 점수는 0 이상이어야 합니다.',
                    max: '5등급 점수는 100.0000 이하여야 합니다.'
                },
                grade5RangeMin: {
                    required: '5등급 범위 최소값을 입력하세요.',
                    number: '5등급 범위 최소값은 숫자로 입력하세요.',
                    min: '5등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '5등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade5RangeMax: {
                    required: '5등급 범위 최대값을 입력하세요.',
                    number: '5등급 범위 최대값은 숫자로 입력하세요.',
                    min: '5등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '5등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade6Score: {
                    required: '6등급 점수를 입력하세요.',
                    number: '6등급 점수는 숫자로 입력하세요.',
                    min: '6등급 점수는 0 이상이어야 합니다.',
                    max: '6등급 점수는 100.0000 이하여야 합니다.'
                },
                grade6RangeMin: {
                    required: '6등급 범위 최소값을 입력하세요.',
                    number: '6등급 범위 최소값은 숫자로 입력하세요.',
                    min: '6등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '6등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade6RangeMax: {
                    required: '6등급 범위 최대값을 입력하세요.',
                    number: '6등급 범위 최대값은 숫자로 입력하세요.',
                    min: '6등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '6등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade7Score: {
                    required: '7등급 점수를 입력하세요.',
                    number: '7등급 점수는 숫자로 입력하세요.',
                    min: '7등급 점수는 0 이상이어야 합니다.',
                    max: '7등급 점수는 100.0000 이하여야 합니다.'
                },
                grade7RangeMin: {
                    required: '7등급 범위 최소값을 입력하세요.',
                    number: '7등급 범위 최소값은 숫자로 입력하세요.',
                    min: '7등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '7등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade7RangeMax: {
                    required: '7등급 범위 최대값을 입력하세요.',
                    number: '7등급 범위 최대값은 숫자로 입력하세요.',
                    min: '7등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '7등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade8Score: {
                    required: '8등급 점수를 입력하세요.',
                    number: '8등급 점수는 숫자로 입력하세요.',
                    min: '8등급 점수는 0 이상이어야 합니다.',
                    max: '8등급 점수는 100.0000 이하여야 합니다.'
                },
                grade8RangeMin: {
                    required: '8등급 범위 최소값을 입력하세요.',
                    number: '8등급 범위 최소값은 숫자로 입력하세요.',
                    min: '8등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '8등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade8RangeMax: {
                    required: '8등급 범위 최대값을 입력하세요.',
                    number: '8등급 범위 최대값은 숫자로 입력하세요.',
                    min: '8등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '8등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade9Score: {
                    required: '9등급 점수를 입력하세요.',
                    number: '9등급 점수는 숫자로 입력하세요.',
                    min: '9등급 점수는 0 이상이어야 합니다.',
                    max: '9등급 점수는 100.0000 이하여야 합니다.'
                },
                grade9RangeMin: {
                    required: '9등급 범위 최소값을 입력하세요.',
                    number: '9등급 범위 최소값은 숫자로 입력하세요.',
                    min: '9등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '9등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade9RangeMax: {
                    required: '9등급 범위 최대값을 입력하세요.',
                    number: '9등급 범위 최대값은 숫자로 입력하세요.',
                    min: '9등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '9등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade10Score: {
                    required: '10등급 점수를 입력하세요.',
                    number: '10등급 점수는 숫자로 입력하세요.',
                    min: '10등급 점수는 0 이상이어야 합니다.',
                    max: '10등급 점수는 100.0000 이하여야 합니다.'
                },
                grade10RangeMin: {
                    required: '10등급 범위 최소값을 입력하세요.',
                    number: '10등급 범위 최소값은 숫자로 입력하세요.',
                    min: '10등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '10등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade10RangeMax: {
                    required: '10등급 범위 최대값을 입력하세요.',
                    number: '10등급 범위 최대값은 숫자로 입력하세요.',
                    min: '10등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '10등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade11Score: {
                    required: '11등급 점수를 입력하세요.',
                    number: '11등급 점수는 숫자로 입력하세요.',
                    min: '11등급 점수는 0 이상이어야 합니다.',
                    max: '11등급 점수는 100.0000 이하여야 합니다.'
                },
                grade11RangeMin: {
                    required: '11등급 범위 최소값을 입력하세요.',
                    number: '11등급 범위 최소값은 숫자로 입력하세요.',
                    min: '11등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '11등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade11RangeMax: {
                    required: '11등급 범위 최대값을 입력하세요.',
                    number: '11등급 범위 최대값은 숫자로 입력하세요.',
                    min: '11등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '11등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade12Score: {
                    required: '12등급 점수를 입력하세요.',
                    number: '12등급 점수는 숫자로 입력하세요.',
                    min: '12등급 점수는 0 이상이어야 합니다.',
                    max: '12등급 점수는 100.0000 이하여야 합니다.'
                },
                grade12RangeMin: {
                    required: '12등급 범위 최소값을 입력하세요.',
                    number: '12등급 범위 최소값은 숫자로 입력하세요.',
                    min: '12등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '12등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade12RangeMax: {
                    required: '12등급 범위 최대값을 입력하세요.',
                    number: '12등급 범위 최대값은 숫자로 입력하세요.',
                    min: '12등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '12등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade13Score: {
                    required: '13등급 점수를 입력하세요.',
                    number: '13등급 점수는 숫자로 입력하세요.',
                    min: '13등급 점수는 0 이상이어야 합니다.',
                    max: '13등급 점수는 100.0000 이하여야 합니다.'
                },
                grade13RangeMin: {
                    required: '13등급 범위 최소값을 입력하세요.',
                    number: '13등급 범위 최소값은 숫자로 입력하세요.',
                    min: '13등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '13등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade13RangeMax: {
                    required: '13등급 범위 최대값을 입력하세요.',
                    number: '13등급 범위 최대값은 숫자로 입력하세요.',
                    min: '13등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '13등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade14Score: {
                    required: '14등급 점수를 입력하세요.',
                    number: '14등급 점수는 숫자로 입력하세요.',
                    min: '14등급 점수는 0 이상이어야 합니다.',
                    max: '14등급 점수는 100.0000 이하여야 합니다.'
                },
                grade14RangeMin: {
                    required: '14등급 범위 최소값을 입력하세요.',
                    number: '14등급 범위 최소값은 숫자로 입력하세요.',
                    min: '14등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '14등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade14RangeMax: {
                    required: '14등급 범위 최대값을 입력하세요.',
                    number: '14등급 범위 최대값은 숫자로 입력하세요.',
                    min: '14등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '14등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade15Score: {
                    required: '15등급 점수를 입력하세요.',
                    number: '15등급 점수는 숫자로 입력하세요.',
                    min: '15등급 점수는 0 이상이어야 합니다.',
                    max: '15등급 점수는 100.0000 이하여야 합니다.'
                },
                grade15RangeMin: {
                    required: '15등급 범위 최소값을 입력하세요.',
                    number: '15등급 범위 최소값은 숫자로 입력하세요.',
                    min: '15등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '15등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade15RangeMax: {
                    required: '15등급 범위 최대값을 입력하세요.',
                    number: '15등급 범위 최대값은 숫자로 입력하세요.',
                    min: '15등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '15등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade16Score: {
                    required: '16등급 점수를 입력하세요.',
                    number: '16등급 점수는 숫자로 입력하세요.',
                    min: '16등급 점수는 0 이상이어야 합니다.',
                    max: '16등급 점수는 100.0000 이하여야 합니다.'
                },
                grade16RangeMin: {
                    required: '16등급 범위 최소값을 입력하세요.',
                    number: '16등급 범위 최소값은 숫자로 입력하세요.',
                    min: '16등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '16등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade16RangeMax: {
                    required: '16등급 범위 최대값을 입력하세요.',
                    number: '16등급 범위 최대값은 숫자로 입력하세요.',
                    min: '16등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '16등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade17Score: {
                    required: '17등급 점수를 입력하세요.',
                    number: '17등급 점수는 숫자로 입력하세요.',
                    min: '17등급 점수는 0 이상이어야 합니다.',
                    max: '17등급 점수는 100.0000 이하여야 합니다.'
                },
                grade17RangeMin: {
                    required: '17등급 범위 최소값을 입력하세요.',
                    number: '17등급 범위 최소값은 숫자로 입력하세요.',
                    min: '17등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '17등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade17RangeMax: {
                    required: '17등급 범위 최대값을 입력하세요.',
                    number: '17등급 범위 최대값은 숫자로 입력하세요.',
                    min: '17등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '17등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade18Score: {
                    required: '18등급 점수를 입력하세요.',
                    number: '18등급 점수는 숫자로 입력하세요.',
                    min: '18등급 점수는 0 이상이어야 합니다.',
                    max: '18등급 점수는 100.0000 이하여야 합니다.'
                },
                grade18RangeMin: {
                    required: '18등급 범위 최소값을 입력하세요.',
                    number: '18등급 범위 최소값은 숫자로 입력하세요.',
                    min: '18등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '18등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade18RangeMax: {
                    required: '18등급 범위 최대값을 입력하세요.',
                    number: '18등급 범위 최대값은 숫자로 입력하세요.',
                    min: '18등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '18등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade19Score: {
                    required: '19등급 점수를 입력하세요.',
                    number: '19등급 점수는 숫자로 입력하세요.',
                    min: '19등급 점수는 0 이상이어야 합니다.',
                    max: '19등급 점수는 100.0000 이하여야 합니다.'
                },
                grade19RangeMin: {
                    required: '19등급 범위 최소값을 입력하세요.',
                    number: '19등급 범위 최소값은 숫자로 입력하세요.',
                    min: '19등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '19등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade19RangeMax: {
                    required: '19등급 범위 최대값을 입력하세요.',
                    number: '19등급 범위 최대값은 숫자로 입력하세요.',
                    min: '19등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '19등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade20Score: {
                    required: '20등급 점수를 입력하세요.',
                    number: '20등급 점수는 숫자로 입력하세요.',
                    min: '20등급 점수는 0 이상이어야 합니다.',
                    max: '20등급 점수는 100.0000 이하여야 합니다.'
                },
                grade20RangeMin: {
                    required: '20등급 범위 최소값을 입력하세요.',
                    number: '20등급 범위 최소값은 숫자로 입력하세요.',
                    min: '20등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '20등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade20RangeMax: {
                    required: '20등급 범위 최대값을 입력하세요.',
                    number: '20등급 범위 최대값은 숫자로 입력하세요.',
                    min: '20등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '20등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade21Score: {
                    required: '21등급 점수를 입력하세요.',
                    number: '21등급 점수는 숫자로 입력하세요.',
                    min: '21등급 점수는 0 이상이어야 합니다.',
                    max: '21등급 점수는 100.0000 이하여야 합니다.'
                },
                grade21RangeMin: {
                    required: '21등급 범위 최소값을 입력하세요.',
                    number: '21등급 범위 최소값은 숫자로 입력하세요.',
                    min: '21등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '21등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade21RangeMax: {
                    required: '21등급 범위 최대값을 입력하세요.',
                    number: '21등급 범위 최대값은 숫자로 입력하세요.',
                    min: '21등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '21등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade22Score: {
                    required: '22등급 점수를 입력하세요.',
                    number: '22등급 점수는 숫자로 입력하세요.',
                    min: '22등급 점수는 0 이상이어야 합니다.',
                    max: '22등급 점수는 100.0000 이하여야 합니다.'
                },
                grade22RangeMin: {
                    required: '22등급 범위 최소값을 입력하세요.',
                    number: '22등급 범위 최소값은 숫자로 입력하세요.',
                    min: '22등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '22등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade22RangeMax: {
                    required: '22등급 범위 최대값을 입력하세요.',
                    number: '22등급 범위 최대값은 숫자로 입력하세요.',
                    min: '22등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '22등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade23Score: {
                    required: '23등급 점수를 입력하세요.',
                    number: '23등급 점수는 숫자로 입력하세요.',
                    min: '23등급 점수는 0 이상이어야 합니다.',
                    max: '23등급 점수는 100.0000 이하여야 합니다.'
                },
                grade23RangeMin: {
                    required: '23등급 범위 최소값을 입력하세요.',
                    number: '23등급 범위 최소값은 숫자로 입력하세요.',
                    min: '23등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '23등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade23RangeMax: {
                    required: '23등급 범위 최대값을 입력하세요.',
                    number: '23등급 범위 최대값은 숫자로 입력하세요.',
                    min: '23등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '23등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade24Score: {
                    required: '24등급 점수를 입력하세요.',
                    number: '24등급 점수는 숫자로 입력하세요.',
                    min: '24등급 점수는 0 이상이어야 합니다.',
                    max: '24등급 점수는 100.0000 이하여야 합니다.'
                },
                grade24RangeMin: {
                    required: '24등급 범위 최소값을 입력하세요.',
                    number: '24등급 범위 최소값은 숫자로 입력하세요.',
                    min: '24등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '24등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade24RangeMax: {
                    required: '24등급 범위 최대값을 입력하세요.',
                    number: '24등급 범위 최대값은 숫자로 입력하세요.',
                    min: '24등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '24등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade25Score: {
                    required: '25등급 점수를 입력하세요.',
                    number: '25등급 점수는 숫자로 입력하세요.',
                    min: '25등급 점수는 0 이상이어야 합니다.',
                    max: '25등급 점수는 100.0000 이하여야 합니다.'
                },
                grade25RangeMin: {
                    required: '25등급 범위 최소값을 입력하세요.',
                    number: '25등급 범위 최소값은 숫자로 입력하세요.',
                    min: '25등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '25등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade25RangeMax: {
                    required: '25등급 범위 최대값을 입력하세요.',
                    number: '25등급 범위 최대값은 숫자로 입력하세요.',
                    min: '25등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '25등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade26Score: {
                    required: '26등급 점수를 입력하세요.',
                    number: '26등급 점수는 숫자로 입력하세요.',
                    min: '26등급 점수는 0 이상이어야 합니다.',
                    max: '26등급 점수는 100.0000 이하여야 합니다.'
                },
                grade26RangeMin: {
                    required: '26등급 범위 최소값을 입력하세요.',
                    number: '26등급 범위 최소값은 숫자로 입력하세요.',
                    min: '26등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '26등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade26RangeMax: {
                    required: '26등급 범위 최대값을 입력하세요.',
                    number: '26등급 범위 최대값은 숫자로 입력하세요.',
                    min: '26등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '26등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade27Score: {
                    required: '27등급 점수를 입력하세요.',
                    number: '27등급 점수는 숫자로 입력하세요.',
                    min: '27등급 점수는 0 이상이어야 합니다.',
                    max: '27등급 점수는 100.0000 이하여야 합니다.'
                },
                grade27RangeMin: {
                    required: '27등급 범위 최소값을 입력하세요.',
                    number: '27등급 범위 최소값은 숫자로 입력하세요.',
                    min: '27등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '27등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade27RangeMax: {
                    required: '27등급 범위 최대값을 입력하세요.',
                    number: '27등급 범위 최대값은 숫자로 입력하세요.',
                    min: '27등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '27등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade28Score: {
                    required: '28등급 점수를 입력하세요.',
                    number: '28등급 점수는 숫자로 입력하세요.',
                    min: '28등급 점수는 0 이상이어야 합니다.',
                    max: '28등급 점수는 100.0000 이하여야 합니다.'
                },
                grade28RangeMin: {
                    required: '28등급 범위 최소값을 입력하세요.',
                    number: '28등급 범위 최소값은 숫자로 입력하세요.',
                    min: '28등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '28등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade28RangeMax: {
                    required: '28등급 범위 최대값을 입력하세요.',
                    number: '28등급 범위 최대값은 숫자로 입력하세요.',
                    min: '28등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '28등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade29Score: {
                    required: '29등급 점수를 입력하세요.',
                    number: '29등급 점수는 숫자로 입력하세요.',
                    min: '29등급 점수는 0 이상이어야 합니다.',
                    max: '29등급 점수는 100.0000 이하여야 합니다.'
                },
                grade29RangeMin: {
                    required: '29등급 범위 최소값을 입력하세요.',
                    number: '29등급 범위 최소값은 숫자로 입력하세요.',
                    min: '29등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '29등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade29RangeMax: {
                    required: '29등급 범위 최대값을 입력하세요.',
                    number: '29등급 범위 최대값은 숫자로 입력하세요.',
                    min: '29등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '29등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade30Score: {
                    required: '30등급 점수를 입력하세요.',
                    number: '30등급 점수는 숫자로 입력하세요.',
                    min: '30등급 점수는 0 이상이어야 합니다.',
                    max: '30등급 점수는 100.0000 이하여야 합니다.'
                },
                grade30RangeMin: {
                    required: '30등급 범위 최소값을 입력하세요.',
                    number: '30등급 범위 최소값은 숫자로 입력하세요.',
                    min: '30등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '30등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade30RangeMax: {
                    required: '30등급 범위 최대값을 입력하세요.',
                    number: '30등급 범위 최대값은 숫자로 입력하세요.',
                    min: '30등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '30등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade31Score: {
                    required: '31등급 점수를 입력하세요.',
                    number: '31등급 점수는 숫자로 입력하세요.',
                    min: '31등급 점수는 0 이상이어야 합니다.',
                    max: '31등급 점수는 100.0000 이하여야 합니다.'
                },
                grade31RangeMin: {
                    required: '31등급 범위 최소값을 입력하세요.',
                    number: '31등급 범위 최소값은 숫자로 입력하세요.',
                    min: '31등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '31등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade31RangeMax: {
                    required: '31등급 범위 최대값을 입력하세요.',
                    number: '31등급 범위 최대값은 숫자로 입력하세요.',
                    min: '31등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '31등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade32Score: {
                    required: '32등급 점수를 입력하세요.',
                    number: '32등급 점수는 숫자로 입력하세요.',
                    min: '32등급 점수는 0 이상이어야 합니다.',
                    max: '32등급 점수는 100.0000 이하여야 합니다.'
                },
                grade32RangeMin: {
                    required: '32등급 범위 최소값을 입력하세요.',
                    number: '32등급 범위 최소값은 숫자로 입력하세요.',
                    min: '32등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '32등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade32RangeMax: {
                    required: '32등급 범위 최대값을 입력하세요.',
                    number: '32등급 범위 최대값은 숫자로 입력하세요.',
                    min: '32등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '32등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade33Score: {
                    required: '33등급 점수를 입력하세요.',
                    number: '33등급 점수는 숫자로 입력하세요.',
                    min: '33등급 점수는 0 이상이어야 합니다.',
                    max: '33등급 점수는 100.0000 이하여야 합니다.'
                },
                grade33RangeMin: {
                    required: '33등급 범위 최소값을 입력하세요.',
                    number: '33등급 범위 최소값은 숫자로 입력하세요.',
                    min: '33등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '33등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade33RangeMax: {
                    required: '33등급 범위 최대값을 입력하세요.',
                    number: '33등급 범위 최대값은 숫자로 입력하세요.',
                    min: '33등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '33등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade34Score: {
                    required: '34등급 점수를 입력하세요.',
                    number: '34등급 점수는 숫자로 입력하세요.',
                    min: '34등급 점수는 0 이상이어야 합니다.',
                    max: '34등급 점수는 100.0000 이하여야 합니다.'
                },
                grade34RangeMin: {
                    required: '34등급 범위 최소값을 입력하세요.',
                    number: '34등급 범위 최소값은 숫자로 입력하세요.',
                    min: '34등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '34등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade34RangeMax: {
                    required: '34등급 범위 최대값을 입력하세요.',
                    number: '34등급 범위 최대값은 숫자로 입력하세요.',
                    min: '34등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '34등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade35Score: {
                    required: '35등급 점수를 입력하세요.',
                    number: '35등급 점수는 숫자로 입력하세요.',
                    min: '35등급 점수는 0 이상이어야 합니다.',
                    max: '35등급 점수는 100.0000 이하여야 합니다.'
                },
                grade35RangeMin: {
                    required: '35등급 범위 최소값을 입력하세요.',
                    number: '35등급 범위 최소값은 숫자로 입력하세요.',
                    min: '35등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '35등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade35RangeMax: {
                    required: '35등급 범위 최대값을 입력하세요.',
                    number: '35등급 범위 최대값은 숫자로 입력하세요.',
                    min: '35등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '35등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade36Score: {
                    required: '36등급 점수를 입력하세요.',
                    number: '36등급 점수는 숫자로 입력하세요.',
                    min: '36등급 점수는 0 이상이어야 합니다.',
                    max: '36등급 점수는 100.0000 이하여야 합니다.'
                },
                grade36RangeMin: {
                    required: '36등급 범위 최소값을 입력하세요.',
                    number: '36등급 범위 최소값은 숫자로 입력하세요.',
                    min: '36등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '36등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade36RangeMax: {
                    required: '36등급 범위 최대값을 입력하세요.',
                    number: '36등급 범위 최대값은 숫자로 입력하세요.',
                    min: '36등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '36등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade37Score: {
                    required: '37등급 점수를 입력하세요.',
                    number: '37등급 점수는 숫자로 입력하세요.',
                    min: '37등급 점수는 0 이상이어야 합니다.',
                    max: '37등급 점수는 100.0000 이하여야 합니다.'
                },
                grade37RangeMin: {
                    required: '37등급 범위 최소값을 입력하세요.',
                    number: '37등급 범위 최소값은 숫자로 입력하세요.',
                    min: '37등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '37등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade37RangeMax: {
                    required: '37등급 범위 최대값을 입력하세요.',
                    number: '37등급 범위 최대값은 숫자로 입력하세요.',
                    min: '37등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '37등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade38Score: {
                    required: '38등급 점수를 입력하세요.',
                    number: '38등급 점수는 숫자로 입력하세요.',
                    min: '38등급 점수는 0 이상이어야 합니다.',
                    max: '38등급 점수는 100.0000 이하여야 합니다.'
                },
                grade38RangeMin: {
                    required: '38등급 범위 최소값을 입력하세요.',
                    number: '38등급 범위 최소값은 숫자로 입력하세요.',
                    min: '38등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '38등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade38RangeMax: {
                    required: '38등급 범위 최대값을 입력하세요.',
                    number: '38등급 범위 최대값은 숫자로 입력하세요.',
                    min: '38등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '38등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade39Score: {
                    required: '39등급 점수를 입력하세요.',
                    number: '39등급 점수는 숫자로 입력하세요.',
                    min: '39등급 점수는 0 이상이어야 합니다.',
                    max: '39등급 점수는 100.0000 이하여야 합니다.'
                },
                grade39RangeMin: {
                    required: '39등급 범위 최소값을 입력하세요.',
                    number: '39등급 범위 최소값은 숫자로 입력하세요.',
                    min: '39등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '39등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade39RangeMax: {
                    required: '39등급 범위 최대값을 입력하세요.',
                    number: '39등급 범위 최대값은 숫자로 입력하세요.',
                    min: '39등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '39등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                grade40Score: {
                    required: '40등급 점수를 입력하세요.',
                    number: '40등급 점수는 숫자로 입력하세요.',
                    min: '40등급 점수는 0 이상이어야 합니다.',
                    max: '40등급 점수는 100.0000 이하여야 합니다.'
                },
                grade40RangeMin: {
                    required: '40등급 범위 최소값을 입력하세요.',
                    number: '40등급 범위 최소값은 숫자로 입력하세요.',
                    min: '40등급 범위 최소값은 0 이상이어야 합니다.',
                    max: '40등급 범위 최소값은 100.0000 이하여야 합니다.'
                },
                grade40RangeMax: {
                    required: '40등급 범위 최대값을 입력하세요.',
                    number: '40등급 범위 최대값은 숫자로 입력하세요.',
                    min: '40등급 범위 최대값은 0 이상이어야 합니다.',
                    max: '40등급 범위 최대값은 1000.0000 이하여야 합니다.'
                },
                earlyAdmissionPhysicalRelativeMemo: {
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
        $(RELATIVE_SAVE_BUTTON_ID).click(function(e) {
            e.preventDefault();
            
            if (!$relativeForm.valid()) return;

            const data = getRelativeFormData($relativeForm);
            const url = '/admin/earlyAdmissionPhysical' + (gender == 'man' ? 'Man' : 'Woman') + 'Relative/' + action;
            
            $.ajax({
                url,
                type: 'POST',
                data,
                success: (response) => {
                    if (response.success) {
                        alert('수시 입시 실기 ' + (gender == 'man' ? '남자' : '여자') + ' 상대평가 점수가 저장되었습니다.');
                        $(RELATIVE_MODAL_ID).modal('hide');
                        updateRelativeButtonState(btnId, 'update', gender);
                    } else {
                        alert('수시 입시 실기 ' + (gender == 'man' ? '남자' : '여자') + ' 상대평가 점수 저장에 실패했습니다.');
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
                    $('#grade' + currentGrade + 'Score, #grade' + currentGrade + 'RangeMin, #grade' + currentGrade + 'RangeMax')
                        .prop('disabled', false);
                    
                    // 다음 등급의 사용 여부 선택 활성화
                    if (currentGrade < 40) {
                        $('#useGrade' + (currentGrade + 1)).prop('disabled', false);
                    }
                } else {
                    // 현재 등급의 입력 필드 비활성화
                    $('#grade' + currentGrade + 'Score, #grade' + currentGrade + 'RangeMin, #grade' + currentGrade + 'RangeMax')
                        .prop('disabled', true);
                    
                    // 현재 등급 이후의 모든 등급 비활성화
                    for (let j = currentGrade + 1; j <= 40; j++) {
                        $('#useGrade' + j).val('N').prop('disabled', true);
                        $('#grade' + j + 'Score, #grade' + j + 'RangeMin, #grade' + j + 'RangeMax')
                            .prop('disabled', true);
                    }
                }
            });
        }
    });
</script>