theme: /

    state: newNode_0
        a:  {{$session.rawRequest.message.from.first_name}} {{$session.rawRequest.message.from.username}}!
            {{$session.rawRequest.message.from.id}}
            /start
        script:
            $reactions.timeout({interval: _.template('1 seconds', {variable: '$session'})($session), targetState: '/newNode_74'});

    state: newNode_97
        if: $session.rawRequest.message.from.id === 1598551410
            go!: /newNode_74
        elseif: $session.rawRequest.message.from.id === 5383352185
            go!: /newNode_74
        elseif: $session.rawRequest.message.from.id === 188329744
            go!: /newNode_74
        elseif: $session.rawRequest.message.from.id === 351421781
            go!: /newNode_74
        else:
            go!: /newNode_7

    state: newNode_74
        if: $session.rawRequest.message.from.id === 685706042
            go!: /newNode_70
        elseif: $session.rawRequest.message.from.id === 1025108269
            go!: /newNode_70
        elseif: $session.rawRequest.message.from.id === 1283832311
            go!: /newNode_70
        elseif: $session.rawRequest.message.from.id === 1405658035
            go!: /newNode_94
        else:
            go!: /newNode_1

    state: newNode_94
        a: По Вашей просьбе, Бот был удалён!
        go!: /newNode_95

    state: newNode_95
        EndSession:

    state: newNode_70
        a: Бот занят, бот пишет F.A.Q.
        go!: /newNode_75

    state: newNode_75
        EndSession:

    state: newNode_1
        a: Выберете раздел
        go!: /newNode_102
    @IntentGroup
        {
          "boundsTo" : "/newNode_1",
          "actions" : [ {
            "buttons" : [ {
              "name" : "Заказ сетки",
              "transition" : "/newNode_99"
            }, {
              "name" : "Задать вопрос в чате мастеров",
              "transition" : "/newNode_33"
            }, {
              "name" : "Водомерка",
              "transition" : "/newNode_72"
            }, {
              "name" : "Оборудование для гидробурения",
              "transition" : "/newNode_99"
            }, {
              "name" : "Буровые мастера",
              "transition" : "/newNode_7"
            }, {
              "name" : "Разное",
              "transition" : "/newNode_57"
            }, {
              "name" : "Выход",
              "transition" : "/newNode_13"
            } ],
            "type" : "buttons"
          } ],
          "global" : true
        }
    state: newNode_102
        state: 1
            intent!: /newNode_102/1

            go!: /newNode_66

        state: 2
            intent!: /newNode_102/2

            go!: /newNode_92

        state: Fallback
            event: noMatch
            go!: /newNode_103
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_102",
                name: "newNode_102 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "Заказ сетки"
                    , transition: "/newNode_99"
                    },
                    {text: "Задать вопрос в чате мастеров"
                    , transition: "/newNode_33"
                    },
                    {text: "Водомерка"
                    , transition: "/newNode_72"
                    },
                    {text: "Оборудование для гидробурения"
                    , transition: "/newNode_99"
                    },
                    {text: "Буровые мастера"
                    , transition: "/newNode_7"
                    },
                    {text: "Разное"
                    , transition: "/newNode_57"
                    },
                    {text: "Выход"
                    , transition: "/newNode_13"
                    },
                  ]);
                }
            });

    state: newNode_13
        a:  Всего доброго!
            Рад был вам помочь.
            /start
        go!: /newNode_23

    state: newNode_23
        EndSession:

    state: newNode_16
        InputText:
            actions = [{"buttons":[{"name":"Отмена","transition":"/newNode_1"}],"type":"buttons"}]
            prompt = Как вас зовут?
            varName = name
            html = Как вас зовут?
            htmlEnabled = false
            then = /newNode_17

    state: newNode_17
        InputPhoneNumber:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Введите номер телефона
            varName = phone
            failureMessage = ["Некорректный номер телефона"]
            then = /newNode_18
            html = Введите номер телефона
            htmlEnabled = false
            failureMessageHtml = ["Некорректный номер телефона"]

    state: newNode_18
        InputText:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Введите текст
            varName = text
            html = Введите текст
            htmlEnabled = false
            then = /newNode_101

    state: newNode_101
        GoogleSheets:
            operationType = writeDataToLine
            integrationId = c46ef122-a5c1-4753-b558-d504b154b6f3
            spreadsheetId = 10WX3Q1GuwKnNs95rYA4L6xGsjnlTm6NJb5xW2Z50Ogk
            sheetName = Лист2
            body = {"values":["{{$session.YTIC}}","{{$session.name}}","{{$session.phone}}","{{$session.aboutTool}}","{{$session.text}}"]}
            okState = /newNode_100
            errorState = /newNode_21

    state: newNode_100
        Email:
            destination = remzone33@gmail.com
            subject = MyWell
            text = {{$session.name}}
                {{$session.YTIC}}\n
                {{$session.text}}\n
                тел. {{$session.phone}}
                ______________________________
                {{$session.rawRequest.message.from.username}}
                {{$session.rawRequest.message.from.first_name}} \n
                id {{$session.rawRequest.message.from.id}}
            files = []
            html = {{$session.name}}<br>{{$session.YTIC}}<br><br>{{$session.text}}<br><br>тел. {{$session.phone}}<br>______________________________<br>{{$session.rawRequest.message.from.username}}<br>{{$session.rawRequest.message.from.first_name}}&nbsp;<br><br>id {{$session.rawRequest.message.from.id}}<br>
            htmlEnabled = true
            okState = /newNode_20
            errorState = /newNode_20

    state: newNode_20
        a: Информация отправлена администратору.
        script:
            $reactions.timeout({interval: _.template('2 seconds', {variable: '$session'})($session), targetState: '/newNode_1'});

    state: newNode_21
        a: Ошибка отправки.
        script:
            $reactions.timeout({interval: _.template('2 seconds', {variable: '$session'})($session), targetState: '/newNode_1'});

    state: newNode_53
        a: Скачать || html = "<a href=\"https://play.google.com/store/apps/details?id=com.malchikdima2011.vodomerka\">Скачать</a>", htmlEnabled = true
        buttons:
            "Поддержать проект" -> /newNode_58
            "iOS" -> /newNode_64
            "Назад" -> /newNode_1

    state: newNode_58
        a:  Поддержать разработчика
            Сбербанк
            2202 2003 0103 1488
            Михаил Вячеславович Р.
        buttons:
            "Назад" -> /newNode_53

    state: newNode_49
        a:  Поддержать разработчика
            Сбербанк
            2202 2003 0103 1488
        go!: /newNode_52

    state: newNode_52
        TelegramPayment:
            providerToken = 401643678:TEST:faffa99f-68fc-41d9-a7bd-edc703bbea4e
            startParameter = true
            paymentTitle = MyWell
            description = На поддержку проекта
            imageUrl = 
            amount = 100
            currency = RUB
            invoicePayload = Из бота
            precheckoutUrl = 
            precheckoutEnabled = false
            okState = /newNode_59
            errorState = /newNode_60
            catchAllState = /newNode_60

    state: newNode_59
        a: Спасибо за поддержку
        script:
            $reactions.timeout({interval: _.template('1 seconds', {variable: '$session'})($session), targetState: '/newNode_53'});

    state: newNode_60
        a: Произошла какая-то ошибка
        script:
            $reactions.timeout({interval: _.template('2 seconds', {variable: '$session'})($session), targetState: '/newNode_53'});

    state: newNode_64
        a:  На данный момент версия для iPhone будет публиковаться 1 раз в месяц.
            Следите за выпуском в чате.
        buttons:
            "Назад" -> /newNode_53

    state: newNode_2
        a: Заказ сетки
        buttons:
            "Тамбовские фильтра" -> /newNode_3
            "Синтетика" -> /newNode_55
            "Нержавейка" -> /newNode_55
            "Назад к списку" -> /newNode_1

    state: newNode_4
        a: Оборудование для буровых работ
        buttons:
            "Барахолка" -> /newNode_67
            "Насосы" -> /newNode_36
            "Буровой инструмент" -> /newNode_6
            "Трубы" -> /newNode_48
            "Добавить производителя" -> /newNode_12
            "Назад к списку" -> /newNode_1

    state: newNode_12
        a:  Если желаете добавить или изменить информацию, свяжитесь с администратором.\n\n
            +7 (904) 656-37-51 Михаил
            +7 (999) 966-85-47 Сергей
        go!: /newNode_15
    @IntentGroup
        {
          "boundsTo" : "/newNode_12",
          "actions" : [ {
            "buttons" : [ {
              "name" : "Написать администратору",
              "transition" : "/newNode_24"
            }, {
              "name" : "Выход",
              "transition" : "/newNode_1"
            } ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_15
        state: 1
            intent: /sys/aimylogic/ru/switch

            go!: /newNode_24

        state: Fallback
            event: noMatch
            go!: /newNode_13
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_15",
                name: "newNode_15 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "Написать администратору"
                    , transition: "/newNode_24"
                    },
                    {text: "Выход"
                    , transition: "/newNode_1"
                    },
                  ]);
                }
            });

    state: newNode_24
        InputText:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Ваш город?
            varName = YTIC
            html = Ваш город?
            htmlEnabled = false
            then = /newNode_16

    state: newNode_33
        a: https://t.me/+0j5f6phbyaY1MDFi
        buttons:
            "Назад" -> /newNode_1

    state: newNode_67
        a: https://t.me/+SQHKY9ra8cA0MTcy
        buttons:
            "Назад" -> /newNode_4

    state: newNode_57
        a: Разное о бурении
        buttons:
            "Топографическая карта высот" -> /newNode_56
            "Обучающие видео" -> /newNode_61
            "Литература" -> /newNode_62
            "Документы" -> /newNode_54
            "Музыка" -> /newNode_63
            "Назад" -> /newNode_1

    state: newNode_54
        a:  Документы
            Карты
        buttons:
            {text: "Договор на бурение", url: "https://docs.google.com/document/d/16OB_f7-6RcTTPUZf94mZKTK65N2cjaue/edit?usp=sharing&ouid=115666112988356948964&rtpof=true&sd=true"}
            {text: "Приложение к договору 1", url: "https://docs.google.com/document/d/1AhemuwnEDJJ-9KljayLFG9B6Pdoi4gEU/edit?usp=sharing&ouid=115666112988356948964&rtpof=true&sd=true"}
            {text: "Приложение к договору 2", url: "https://docs.google.com/document/d/1e1dZ4tCSpFL49QUPxsSY0un7AcqXPl16/edit?usp=sharing&ouid=115666112988356948964&rtpof=true&sd=true"}
            {text: "Паспорт скважины", url: "https://docs.google.com/document/d/1EiBs-tE3KW9HtvR-HSx9cvNveZkhEHmg/edit?usp=sharing&ouid=115666112988356948964&rtpof=true&sd=true"}
            {text: "Акт выполненных работ", url: "https://docs.google.com/document/d/1NdwofQHYr8gILysOKVHpybFW8yxvzx0D/edit?usp=sharing&ouid=115666112988356948964&rtpof=true&sd=true"}
            "Назад" -> /newNode_57

    state: newNode_56
        a:  Топографическая карта
            https://ru-ru.topographic-map.com/maps/eth6/Россия/
        buttons:
            "Назад" -> /newNode_57

    state: newNode_61
        video: https://youtu.be/NzvrbBhaVdM || name = "NzvrbBhaVdM"
        buttons:
            "Назад" -> /newNode_57
            "Ещё видео" -> /newNode_68

    state: newNode_68
        video: https://youtu.be/mtbLo5JoVEk || name = "mtbLo5JoVEk"
        buttons:
            "Назад" -> /newNode_57

    state: newNode_62
        a: Всё о бурении
        buttons:
            {text: "Стандартные вопросы", url: "https://drive.google.com/file/d/1IzqwkMyugHWa7Y9Gc6WGp8re9cZ9aKQO/view?usp=sharing"}
            {text: "Питер Болл. Бурение скважин", url: "https://drive.google.com/file/d/1JVw93Fbkr9rNAF0sTYHpiqwBug2dsgo9/view?usp=sharing"}
            {text: "Спутник буровика", url: "https://drive.google.com/file/d/13FdaxkdcBL-GrjshEfgS1lmXCRBZYTvN/view?usp=sharing"}
            {text: "Гидравлические системы", url: "https://drive.google.com/file/d/190DMcAUW6ySBd5Od5UhZZgILuwThfFuv/view?usp=sharing"}
            {text: "Основы технологии бурения", url: "https://drive.google.com/file/d/1E_91zkfmuG1M3KDTPIKwGNvxC1Tb_nnn/view?usp=sharing"}
            {text: "Промышленная безопастность", url: "https://drive.google.com/file/d/1Z0juiMJSFbwZOIiQB7p8mgg_hmPbiepj/view?usp=sharing"}
            {text: "Состояние недр РФ 2012", url: "https://drive.google.com/file/d/1LSQcy0kwk-8ctTqWrdklGK2zKESBdEjk/view?usp=sharing"}
            "Назад" -> /newNode_57

    state: newNode_63
        a: ;)
        random:
            audio: https://248305.selcdn.ru/zfl_prod/1000028872/261297746/audio/4bi5MQNUDCYxZ7CL.mp3?channels={"incompatible":["GOOGLE_ASSISTANCE","OUTGOING_CALLS","ALISA","ALEXA"],"compatible":["WHATSAPP","TELEGRAM","AIMYBOX","FACEBOOK","VK"]} || name = "PEDR_-_Pombur_(mp3vk.co).mp3"
        buttons:
            "Назад" -> /newNode_57

    state: newNode_3
        a:  Фильтра из г.Тамбов\n
            Используйте WhatsApp
            +7915 870-14-15
            Андрей Владимирович С.
            https://ntp68.ru/
        image: https://248305.selcdn.ru/zfl_prod/1000028872/261297746/McVYK6qyW5ng5uqf.jpg
        buttons:
            "Назад" -> /newNode_2

    state: newNode_36
        a: Выберите поставщика.
        buttons:
            "Omnigena" -> /newNode_38
            "К списку" -> /newNode_4

    state: newNode_38
        a:  Omnigena\n
            г.Москва Кательники.
            +79036267627 Валерий\n
            https://omnigena.info
            https://pompy-polska.com || html = "Omnigena\n\nг.Москва Кательники.\n+79036267627 Валерий<br><br>", htmlEnabled = false
        buttons:
            "Назад" -> /newNode_36

    state: newNode_55
        a:  Москва
            https://vk.com/id527562199
            Труба-обсадная.ру
            +79688392832\n
            Екатеренбург
            .Уралнефтестрой || html = "<b>Москва</b><br><a href=\"https://vk.com/id527562199\">https://vk.com/id527562199</a><br><a href=\"https://snabi.ru/products/\">Труба-обсадная.ру</a><br>+79688392832<br><br>Екатеренбург<br><a href=\"https://u-ns.ru/product-category/setka/\">.Уралнефтестрой</a><br><br><br><br>", htmlEnabled = true
        buttons:
            "Назад" -> /newNode_2

    state: newNode_48
        a: https://ntp68.ru/
        image: https://248305.selcdn.ru/zfl_prod/1000028872/261297746/UUnI9TDpL122aVaW.jpg
        buttons:
            "Назад" -> /newNode_4

    state: newNode_73
        a: STAB-A || html = "<u><a href=\"https://ntp68.ru/product/stab-стабилизатор-бурового-раствора/?v=f9308c5d0596\">STAB-A</a></u>", htmlEnabled = true
        buttons:
            "Назад" -> /newNode_6

    state: newNode_98
        EndSession:

    state: newNode_5
        a: Насосное оборудование
        buttons:
            "Рабочие насосы" -> /newNode_12
            "Поверхностные" -> /newNode_12

    state: newNode_6
        a: Буровой инструмент
        buttons:
            "Ручной метод" -> /newNode_41
            "Аппаратный метод" -> /newNode_43
            "Стабилизатор бурового раствора" -> /newNode_73
            "Назад к списку" -> /newNode_4

    state: newNode_41
        a: Буровой инструмент для ручного гидробурения, иглофильтры, штанги, ручки, кондукторы
        buttons:
            "Раменское" -> /newNode_42
            "Н.Новгород" -> /newNode_44
            "Старый Оскол" -> /newNode_47
            "Великие Луки" -> /newNode_69
            "Поворотная муфта" -> /newNode_71
            "Назад" -> /newNode_6

    state: newNode_42
        a:  Буровой инструмент.\n
            Буровой инструмент для ручного гидробурения, иглофильтры, штанги, ручки, кондукторы\n
            +79265570934
        buttons:
            "К списку" -> /newNode_41

    state: newNode_43
        a: Аппаратное гидробурение
        buttons:
            "Н.Новгород" -> /newNode_44
            "Старый Оскол" -> /newNode_47
            "К списку" -> /newNode_6

    state: newNode_44
        a:  Буровое оборудование для ручно́го и аппаратного гидробурения. Буровые штанги, кондукторы, ловушки, вертлюги, буровые долотья\n
            Василий Дикунов.
            +79082300533
        buttons:
            "К списку" -> /newNode_6

    state: newNode_69
        a:  Степан Баранов
            Великие Луки, Псковская область.
            Доставка
            Тел. +79062248126 Viber, WhatsApp
            https://vk.link/clubpoleznijkanal
        buttons:
            "Назад" -> /newNode_41

    state: newNode_47
        a:  ОсколБур
            Андрей
            +79040809707
            http://www.oskolbur.ru/
        buttons:
            "Назад" -> /newNode_41

    state: newNode_72
        image: https://248305.selcdn.ru/zfl_prod/1000028872/261297746/t5eyZwPeLOoMtjJA.jpg
        buttons:
            "Открыть в Google Play" -> /newNode_53
            "iOS" -> /newNode_76
            "Назад" -> /newNode_1

    state: newNode_76
        a: https://pombur.github.io/vodomerka/index.html
        buttons:
            "Назад" -> /newNode_72

    state: newNode_82
        if: $session.index = $session.index + 1
            go!: /newNode_83

    state: newNode_83
        if: $session.index % 5 == 0
            go!: /newNode_86
        else:
            go!: /newNode_79

    state: newNode_86
        a: Вывести ещё 5 ➡️
        buttons:
            "Да ➡️" -> /newNode_79

    state: newNode_7
        a: Буровые мастера по регионам
        buttons:
            "Найти мастера 🔍" -> /newNode_92
            "Добавиться" -> /newNode_66
            "Главное меню" -> /newNode_1

    state: newNode_66
        InputText:
            actions = [{"buttons":[{"name":"Назад","transition":"/newNode_7"}],"type":"buttons"}]
            prompt = Ваш город
            varName = YTIC
            html = Ваш город<br>
            htmlEnabled = true
            then = /newNode_89

    state: newNode_89
        InputNumber:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Введите номер региона
            varName = numberReg
            failureMessage = ["Введите номер региона"]
            then = /newNode_26
            minValue = 1
            maxValue = 999
            html = Введите номер региона
            htmlEnabled = false
            failureMessageHtml = ["Введите номер региона"]

    state: newNode_26
        InputText:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Введите ваше имя
            varName = name
            html = Введите ваше имя
            htmlEnabled = false
            then = /newNode_32

    state: newNode_28
        InputText:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Кратко о себе:
                Метод бурения
                Район или область бурения
            varName = about
            html = <span style="background-color: var(--white); letter-spacing: 0px;">Кратко о себе:</span><br><i>Метод бурения<br><span style="background-color: var(--white); letter-spacing: 0px;">Район или область бурения</span></i><br><br>
            htmlEnabled = true
            then = /newNode_29

    state: newNode_29
        GoogleSheets:
            operationType = writeDataToLine
            integrationId = c46ef122-a5c1-4753-b558-d504b154b6f3
            spreadsheetId = 10WX3Q1GuwKnNs95rYA4L6xGsjnlTm6NJb5xW2Z50Ogk
            sheetName = Лист1
            body = {"values":["{{$session.YTIC}}","{{$session.name}}","{{$session.phone}}","{{$session.about}}","{{$session.numberReg}}","{{$session.rawRequest.message.from.id}}"]}
            okState = /newNode_100
            errorState = /newNode_21

    state: newNode_32
        InputPhoneNumber:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = Введите номер телефона
            varName = phone
            failureMessage = ["Некорректный номер телефона"]
            then = /newNode_28
            html = Введите номер телефона
            htmlEnabled = false
            failureMessageHtml = ["Некорректный номер телефона"]

    state: newNode_92
        a: Если поиск не дал результата, используйте название  Административного центра а также его область ‼ || html = "Если поиск не дал результата, используйте название&nbsp; Административного центра а также его область ‼", htmlEnabled = true
        go!: /newNode_93

    state: newNode_93
        InputText:
            actions = [{"buttons":[{"name":"К списку","transition":"/newNode_7"}],"type":"buttons"}]
            prompt = В каком городе или области искать? 🔍
            varName = city
            html = В каком городе или области искать? 🔍
            htmlEnabled = false
            then = /newNode_34

    state: newNode_34
        HttpRequest:
            url = https://tools.aimylogic.com/api/googlesheet2json?sheet=Лист1&id=10WX3Q1GuwKnNs95rYA4L6xGsjnlTm6NJb5xW2Z50Ogk
            method = GET
            body = 
            okState = /newNode_77
            errorState = /newNode_92
            timeout = 0
            headers = []
            vars = [{"name":"items","value":"_.where($session.httpResponse, {city:$session.city})"}]

    state: newNode_77
        if: $session.items.first()
            go!: /newNode_78
        else:
            go!: /newNode_85

    state: newNode_78
        if: $session.index = 0
            go!: /newNode_79
        else:
            go!: /newNode_79

    state: newNode_79
        a:  {{$session.items.current().name}}                     {{$session.items.current().reg}} RUS 🇷🇺
            {{$session.items.current().phone}} \n
            {{$session.items.current().about}}; || html = "{{$session.items.current().name}}&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;{{$session.items.current().reg}} RUS 🇷🇺<br>{{$session.items.current().phone}}&nbsp;<br><br>{{$session.items.current().about}};", htmlEnabled = true
        # Transition /newNode_80
        go!: /newNode_81

    state: newNode_81
        if: $session.items.next()
            go!: /newNode_82
        else:
            go!: /newNode_84

    state: newNode_84
        a: ❌ Больше в {{$session.city}} мастеров нет
        # Transition /newNode_88
        go!: /newNode_92

    state: newNode_85
        a:  Похоже что из {{$session.city}} мастеров нет, или они не зарегистрированы
            ☹ || html = "Похоже что из {{$session.city}} мастеров нет, или они не зарегистрированы<br>☹<br>", htmlEnabled = true
        # Transition /newNode_87
        go!: /newNode_92
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [ {
            "buttons" : [ {
              "name" : "Отмена",
              "transition" : "/newNode_7"
            } ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_65
        state: 1
            intent: /sys/aimylogic/ru/city

            go!: /newNode_26

        state: 2
            intent: /newNode_65/2

            go!: /

        state: Fallback
            event: noMatch
            go!: /newNode_7
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_65",
                name: "newNode_65 buttons",
                handler: function($context) {
                  $reactions.buttons([
                    {text: "Отмена"
                    , transition: "/newNode_7"
                    },
                  ]);
                }
            });

    state: newNode_71
        a:  Поворотная муфта
            Дмитрий +79018030933
        image: https://drive.google.com/file/d/1VsGngF4JZ2XWu-IylCmSvQw4JARBAOy8/view?usp=sharing
        image: https://drive.google.com/file/d/1VxeWM25_FpJAik-Uj6H7Z1d82v9BgH1p/view?usp=sharing
        buttons:
            "Назад" -> /newNode_41

    state: newNode_90
        InputNumber:
            actions = [{"buttons":[],"type":"buttons"}]
            prompt = В каком регионе искать мастера?
            varName = numberReg
            failureMessage = ["Введите номер региона"]
            then = /newNode_91
            minValue = 1
            maxValue = 999
            html = В каком регионе искать мастера?
            htmlEnabled = false
            failureMessageHtml = ["Введите номер региона"]

    state: newNode_91
        HttpRequest:
            url = https://tools.aimylogic.com/api/googlesheet2json?sheet=Лист1&id=10WX3Q1GuwKnNs95rYA4L6xGsjnlTm6NJb5xW2Z50Ogk
            method = GET
            body = 
            okState = /newNode_46
            errorState = /newNode_46
            timeout = 0
            headers = []
            vars = [{"name":"itemsReg","value":"_.where($session.httpResponse, {reg:$session.numberReg})"}]

    state: newNode_46
        a:  {{$session.itemsReg.next().reg}}
            {{$session.itemsReg.current().name}}
            +{{$session.itemsReg.current().phone}}\n
            {{$session.itemsReg.current().abaut}};
        buttons:
            "Ещё" -> /newNode_51
            "К списку" -> /newNode_7

    state: newNode_51
        a:  {{$session.itemsReg.next().reg}}
            {{$session.itemsReg.current().name}}
            +{{$session.itemsReg.current().phone}}\n
            {{$session.itemsReg.current().abaut}};
        buttons:
            "Ещё" -> /newNode_46
            "Назад к списку" -> /newNode_7

    state: newNode_103
        EndSession:
    @IntentGroup
        {
          "boundsTo" : "",
          "actions" : [ {
            "buttons" : [ ],
            "type" : "buttons"
          } ],
          "global" : false
        }
    state: newNode_96
        state: 1
            intent: /sys/aimylogic/ru/city

            go!: /
        init:
            $jsapi.bind({
                type: "postProcess",
                path: "/newNode_96",
                name: "newNode_96 buttons",
                handler: function($context) {
                }
            });

    state: newNode_99
        a: Раздел удалён.
        buttons:
            "Я производитель, поставщик" -> /newNode_12
            "Назад" -> /newNode_1