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

    cy.get('a').should('have.attr', 'href', '#/new').click()

    cy.get('input')
      .type(TITLE)

    cy.get('textarea')
      .type(CONTENT)

    cy.get('button[name="add"]')
      .click()

    cy.wait(1000)

    cy.get('p.box-title')
      .contains(TITLE)

    cy.get('.box-body')
      .contains(CONTENT)

    cy.get('.note-container').should('contain', TITLE)

    // delete it
    cy.get('.note-container').contains(TITLE).parent().find('[name="delete"]').click()

    // check if added note still exists
    cy.get('.note-container').should('not.contain', TITLE)
  })
})
