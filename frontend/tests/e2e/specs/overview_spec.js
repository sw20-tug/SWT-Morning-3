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
  it('mark note as completed', () => {
    cy.visit('/')
    cy.get('.note-container').first().parent().find('[name="checkbox-completed"]').first().check({ force: true }).should('be.checked')
    cy.get('.note-container').first().parent().find('[name="checkbox-completed"]').first().click()
    cy.get('.note-container').first().parent().find('[name="checkbox-completed"]').first().should('not.be.checked')  cy.get('.note-container').first().parent().find('[name="dateCompleted"]').first().should('exist')
  })
})


