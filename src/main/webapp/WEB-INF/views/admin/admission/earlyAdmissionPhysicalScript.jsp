<script>
    $(document).ready(function() {
        $('.early-admission-physical-man-absolute-score-modal').show(function(e) {
            e.preventDefault();
            const id = $(this).data('id');
            console.log(id);
            
            for(let i = 1; i <= 40; i++) {
                if(i == id) {
                    $('#earlyAdmissionPhysicalManAbsoluteScoreModal' + i).show();
                } else {
                    $('#earlyAdmissionPhysicalManAbsoluteScoreModal' + i).hide();
                }
            }
        });
    });
</script>