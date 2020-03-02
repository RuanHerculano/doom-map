let cardIndex = 0;

function prependCrime() {
    cardIndex++;
    const crime = jQuery('#select-crimes option:selected').text();
    const timeOfEvent = jQuery('#input-datetime-local-time-of-event').val();
    const cep = jQuery('#input-text-cep').val();

    jQuery('#crime-cards').prepend(
        `<div class="card mt-4" id="crime-card-number-${cardIndex}">
            <h5 class="card-header">
                Crime
                <button type="button" class="close" aria-label="Fechar" onclick="removeCard(${cardIndex})">
                    <span aria-hidden="true">&times;</span>
                </button>
            </h5>
            <div class="card-body">
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Crime</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${crime}</p>
                        <input type="hidden" name="crimes[${cardIndex}][crime]" value="${crime}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Hora</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${timeOfEvent}</p>
                        <input type="hidden" name="crimes[${cardIndex}][timeOfEvent]" value="${timeOfEvent}">
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">CEP</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${cep}</p>
                        <input type="hidden" name="crimes[${cardIndex}][cep]" value="${cep}">
                    </div>
                </div>
            </div>
        </div>`
    );

    jQuery('#select-crimes').val('undefined');
    jQuery('#input-datetime-local-time-of-event').val('2011-08-19T13:45:00');
    jQuery('#input-text-cep').val('');

    jQuery('#button-create-report').removeAttr('disabled');
}

function removeCard(cardIndex) {
    jQuery(`#crime-card-number-${cardIndex}`).remove();

    const amountCards = $("#crime-cards").children().length;
    if (amountCards === 0) {
        jQuery('#button-create-report').attr("disabled", true);
    }
}
