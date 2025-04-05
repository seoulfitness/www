<script>
    $(document).ready(function() {
        // 절대평가 점수 버튼 클릭 시
        $('.btn-absolute-score').click(function(e) {
            e.preventDefault();
            const id = $(this).data('id');
            const admissionId = $(this).data('admission-id');
            const subjectId = $(this).data('subject-id');
            const earlyAdmissionPhysicalId = $(this).data('early-admission-physical-id');
            const subjectName = $(this).data('subject-name');
            const gender = $(this).data('gender');
            const action = $(this).data('action');
            console.log(id, admissionId, earlyAdmissionPhysicalId, subjectId, subjectName, gender, action);

            if (action == 'create') {
                // 1등급 초기화
                $('#earlyAdmissionPhysicalAbsoluteScoreModal #useGrade1').val('Y'); // 사용으로 설정
                $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade1Score').val('0.0000');
                $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade1RecordMin').val('0.0000');
                $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade1RecordMax').val('0.0000');

                // 2등급부터 40등급까지 초기화
                for (let i = 2; i <= 40; i++) {
                    $('#earlyAdmissionPhysicalAbsoluteScoreModal #useGrade' + i).val('N'); // 사용안함으로 설정
                    $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade' + i + 'Score').val('0.0000');
                    $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade' + i + 'RecordMin').val('0.0000');
                    $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade' + i + 'RecordMax').val('0.0000');
                    $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade' + i + 'Score').prop('disabled', true);
                    $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade' + i + 'RecordMin').prop('disabled', true);
                    $('#earlyAdmissionPhysicalAbsoluteScoreModal #grade' + i + 'RecordMax').prop('disabled', true);
                }
            } else if (action == 'update') {
                $.ajax({
                    url: '/admin/earlyAdmissionPhysical' + gender + 'Absolute/read',
                    type: 'GET',
                    data: {
                        admissionId: admissionId,
                        earlyAdmissionPhysicalId: earlyAdmissionPhysicalId,
                        earlyAdmissionPhysicalAbsoluteId: earlyAdmissionPhysicalAbsoluteId,
                    },
                    success: function(response) {
                        console.log(response);
                    },
                    error: function(error) {
                        console.log(error);
                    }
                });
            }

            // 절대평가 점수 입력 모달 표시
            $('#earlyAdmissionPhysicalAbsoluteScoreModal').modal('show');
        });
    });
</script>