describe('Overview page', () => {
  it('successfully loads', () => {
    cy.visit('/')
  })

  it('lists notes if any are present', () => {
    cy.visit('/')
    cy.get('body').then(body => {
      const notes = body.find('.note')

      if (notes.length > 0) {
        /*  each note should have a header and a body */
        notes.forEach(n => {
          n.get('.note-header').should.exist
          n.get('.note-body').should.exist
        })
      }
    })
  })

  it('sorts notes by date', () => {
    cy.visit('/')

    cy.get('#btn-sort-by-date').click()

    cy.get('body').then(body => {
      const notes = Array.prototype.slice.call(body.find('.box'))
      const notesWhichAreNotPinned = notes
        .filter(note => note.querySelector('a[name="pin"]') != null)

      const datesOfNotesWhichAreNotPinned = notesWhichAreNotPinned
        .map(note => {
          const dateTime = note.querySelector('.box-date').innerHTML
          return dateTime.split(',').map(_ => _.trim())
        })

      const sortedDates = datesOfNotesWhichAreNotPinned.sort((a, b) => {
        if (a[0] < b[0]) return -1
        if (a[0] > b[0]) return 1

        if (a[1] < b[1]) return -1
        if (a[1] > b[1]) return 1

        return 0
      })

      expect(datesOfNotesWhichAreNotPinned).to.eq(sortedDates)
    })
  })

  it('sorts notes by title', () => {
    cy.visit('/')

    cy.get('#btn-sort-by-title').click()

    cy.get('body').then(body => {
      const notes = Array.prototype.slice.call(body.find('.box'))
      const notesWhichAreNotPinned = notes
        .filter(note => note.querySelector('a[name="pin"]') != null)

      const titlesOfNotesWhichAreNotPinned = notesWhichAreNotPinned
        .map(note => note.querySelector('.box-title').firstChild.textContent.trim())

      const sortedTitles = titlesOfNotesWhichAreNotPinned.sort((a, b) => a.localeCompare(b))

      expect(titlesOfNotesWhichAreNotPinned).to.eq(sortedTitles)
    })
  })

  it('filters notes by date', () => {
    cy.visit('/')

    cy.get('#btn-reset-filters').click()

    cy.get('body').then(body => {
      const allDates = Array.prototype.slice.call(body.find('.box'))
        .map(el => {
          const dateTime = el.querySelector('.box-date').firstChild.textContent.trim()
          const [_date, _] = dateTime.split(',').map(_ => _.trim())
          return _date
        })
      
      const randomDate = allDates[Math.floor(Math.random(allDates.length))]

      cy.get('#dateInput').type(randomDate)
      cy.get('#btn-filter-by-date').click()
    })

    cy.get('body').then(body => {
      body.find('.box-date').each((_, el) =>
        expect(el.innerHTML.trim().startsWith(randomDate)).to.equal(true))
    })
  })
})
