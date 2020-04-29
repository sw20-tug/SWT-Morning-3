describe('Delete note', () => {
  it('can add new note and delete it', () => {
    const generateRandomString = length => {
      let alphabet = 'abcdefghijklmnopqrstuvwxyz0123456789'
      let text = ''
      for (let i = 0; i < length; i++)
        text += alphabet.charAt(Math.floor(Math.random() * alphabet.length))
      return text
    }

    const TITLE = generateRandomString(13)
    const CONTENT = generateRandomString(33)

    cy.visit('/')

    cy.visit('/#/new')

    cy.get('input')
      .type(TITLE)

    cy.get('textarea')
      .type(CONTENT)

    cy.get('button')
      .click()

    cy.visit('/')

    cy.get('p.note-title')
      .contains(TITLE)

    cy.get('.note-body')
      .contains(CONTENT)

    cy.get('.note').should('contain', TITLE)

    // delete it
    cy.get('.note').contains(TITLE).parent().parent().find('.note-footer').contains('delete').click()

    // check if added note still exists
    cy.get('.note').should('not.contain', TITLE)
  })
})
