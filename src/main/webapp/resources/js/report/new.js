let cardIndex = 0;
let crimes = [];

function prependCrime() {
    cardIndex++;
    const crimeName = jQuery('#select-crimes option:selected').text();
    const crimeID = jQuery('#select-crimes option:selected').val();
    const timeOfEvent = new Date(jQuery('#input-datetime-local-time-of-event').val());
    const cep = jQuery('#input-text-cep').val();

    const crime = {
        crimeID: crimeID,
        timeOfEvent: formatDatetime(timeOfEvent),
        cep: cep,
    };

    crimes.push(crime);
    console.log(crimes);

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
                        <p class="form-control-static">${crimeName}</p>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">Hora</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${timeOfEvent.toLocaleTimeString()}</p>
                    </div>
                </div>
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label">CEP</label>
                    <div class="col-sm-10">
                        <p class="form-control-static">${cep}</p>
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

function submitNewReport(event) {
    event.preventDefault();

    $.ajax({
        type: 'POST',
        url: '/report/create',
        data: JSON.stringify(crimes),
        contentType: 'application/json; charset=utf-8',
        success: function () {
            location.href = '/report';
        }
    });
}

function removeCard(cardIndex) {
    jQuery(`#crime-card-number-${cardIndex}`).remove();

    const amountCards = $("#crime-cards").children().length;
    if (amountCards === 0) {
        jQuery('#button-create-report').attr("disabled", true);
    }
}

function formatDatetime(date) {
    let yyyy = date.getFullYear();
    let dd = date.getDate();
    let MM = (date.getMonth() + 1);

    if (dd < 10)
        dd = "0" + dd;

    if (MM < 10)
        MM = "0" + MM;

    const formattedDatetime = yyyy + "-" + MM + "-" + dd;

    let HH = date.getHours();
    let mm = date.getMinutes();
    let ss = date.getSeconds();

    if (HH < 10)
        HH = "0" + HH;

    if (mm < 10)
        mm = "0" + mm;

    if (ss < 10)
        ss = "0" + ss;

    return formattedDatetime + " " + HH + ":" + mm + ":" + ss;
}