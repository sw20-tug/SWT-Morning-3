describe('Test Note tags', () => {
  it('successfully loads', () => {
  cy.visit('/')
  cy.get('[name="edit"]').first().click()
})

  it('successfully edits tags', () => {
    const generateRandomString = length => {
    let alphabet = 'abcdefghijklmnopqrstuvwxyz0123456789'
    let text = ''
    for (let i = 0; i < length; i++)
    text += alphabet.charAt(Math.floor(Math.random() * alphabet.length))
    return text
    }
    const TITLE = generateRandomString(13)
    const CONTENT = generateRandomString(33)
    const TAG = generateRandomString(5)

    cy.visit('/')
    cy.get('[name="edit"]').first().click()
    cy.get('input').type('{selectall}{del}'+TITLE)
    cy.get('textarea').type('{selectall}{del}'+CONTENT)

    cy.get('button[id=addTagButton]').click()
    cy.get('input[id=inputTag]').type(TAG)
    cy.get('button[id=saveTagButton]').click()


    cy.get('button[name="edit"]').click()

    cy.get('p.box-title')
      .contains(TITLE)

    cy.get('.box-body')
      .contains(CONTENT)

    cy.get('p.box-title')
      .contains(TAG)
  })

  it('successfully removes tags', () => {
    const generateRandomString = length => {
    let alphabet = 'abcdefghijklmnopqrstuvwxyz0123456789'
    let text = ''
    for (let i = 0; i < length; i++)
    text += alphabet.charAt(Math.floor(Math.random() * alphabet.length))
    return text
    }

    const TAG = generateRandomString(5)

    cy.visit('/')
    cy.get('[name="edit"]').first().click()

    cy.get('button[id=addTagButton]').click()
    cy.get('input[id=inputTag]').type(TAG)
    cy.get('button[id=saveTagButton]').click()


    cy.get('button[name="edit"]').click()

    cy.wait(1000)

    // find the correct edit note that contains TAG
    cy.get('.box').contains(TAG).parent().parent().find('[name="edit"]').click()

    cy.get('.tag').contains(TAG).find('button[id=removeTagButton]').click()
    cy.get('button[name="edit"]').click()

    cy.get('.note-container').first().should('not.contain', TAG)

  })


})
