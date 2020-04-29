describe('Edit note page', () => {
  it('successfully loads', () => {
    cy.visit('/')
    cy.get('a').contains('edit').click()
  })

  it('successfully edits', () => {
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
    cy.get('a').contains('edit').click()
    cy.get('input').type('{selectall}{del}'+TITLE)
    cy.get('textarea').type('{selectall}{del}'+CONTENT)

    cy.get('button').click()

    cy.get('p.note-title')
      .contains(TITLE)

    cy.get('.note-body')
      .contains(CONTENT)
  })
})
